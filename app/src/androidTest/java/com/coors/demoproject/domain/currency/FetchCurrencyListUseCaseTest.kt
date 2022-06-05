package com.coors.demoproject.domain.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.coors.commoncore.result.Result
import com.coors.commoncore.result.data
import com.coors.commoncore.result.exception
import com.coors.demoproject.CoroutineScope
import com.coors.demoproject.MainCoroutineRule
import com.coors.demoproject.MockData
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.data.currency.CurrencyInfoMapper
import com.coors.demoproject.data.currency.CurrencyRepository
import com.coors.demoproject.data.currency.Mapper
import com.coors.demoproject.runBlockingTest
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FetchCurrencyListUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var fetchCurrencyListUseCase: FetchCurrencyListUseCase

    @MockK(relaxed = true)
    private lateinit var currencyRepository: CurrencyRepository

    private lateinit var moshi: Moshi

    private lateinit var mapper: Mapper<String, List<CurrencyInfo>>

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        mapper = CurrencyInfoMapper(moshi)

        MockKAnnotations.init(this)
    }

    @Test
    fun fetchCurrencyListSuccess() {
        coroutineRule.runBlockingTest {
            fetchCurrencyListUseCase = FetchCurrencyListUseCase(
                repository = currencyRepository,
                currencyInfoMapper = mapper,
                coroutineDispatcher = coroutineRule.testDispatcher
            )

            coEvery {
                currencyRepository.fetchCurrencyListJson()
            } returns MockData.getJsonString()

            fetchCurrencyListUseCase(Unit)
                .onEach { result ->
                    assertThat(result).isInstanceOf(Result.Success::class.java)
                    assertThat(result.data?.size ?: 0).isEqualTo(14)
                    assertThat(result.data?.first()).isNotNull()
                    assertThat(result.data?.first()?.name).isEqualTo("Bitcoin")
                    assertThat(result.data?.last()?.name).isEqualTo("USD Coin")
                }
                .launchIn(coroutineRule.CoroutineScope())
        }
    }

    @Test
    fun fetchCurrencyListFailed() {
        coroutineRule.runBlockingTest {
            fetchCurrencyListUseCase = FetchCurrencyListUseCase(
                repository = currencyRepository,
                currencyInfoMapper = mapper,
                coroutineDispatcher = coroutineRule.testDispatcher
            )

            coEvery {
                currencyRepository.fetchCurrencyListJson()
            } returns ""

            fetchCurrencyListUseCase(Unit)
                .onEach { result ->
                    assertThat(result).isInstanceOf(Result.Error::class.java)
                    assertThat(result.exception?.message).contains("java.io.EOFException: End of input")
                }
                .launchIn(coroutineRule.CoroutineScope())
        }
    }
}