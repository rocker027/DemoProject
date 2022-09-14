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
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
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
class SortCurrencyUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var sortingCurrencyListUseCase: SortingCurrencyListUseCase

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
    fun checkCurrencyListSortBySymbolAscending() {
        coroutineRule.runBlockingTest {
            sortingCurrencyListUseCase = SortingCurrencyListUseCase(
                repository = currencyRepository,
                currencyInfoMapper = mapper,
                coroutineDispatcher = coroutineRule.testDispatcher
            )

            coEvery {
                currencyRepository.fetchCurrencyListJson()
            } returns MockData.getJsonString()

            sortingCurrencyListUseCase(SortingCurrencyListUseCase.Params(isAscending = true))
                .onEach { result ->
                    result.shouldBeTypeOf<Result.Success<List<CurrencyInfo>>>()
                    result.data.size.shouldBe(14)
                    result.data.first().shouldNotBeNull()
                    result.data.first().symbol.shouldBe("BCH")
                    result.data.last().symbol.shouldBe("XRP")
                }
                .launchIn(coroutineRule.CoroutineScope())
        }
    }

    @Test
    fun checkCurrencyListSortBySymbolDescending() {
        coroutineRule.runBlockingTest {
            sortingCurrencyListUseCase = SortingCurrencyListUseCase(
                repository = currencyRepository,
                currencyInfoMapper = mapper,
                coroutineDispatcher = coroutineRule.testDispatcher
            )

            coEvery {
                currencyRepository.fetchCurrencyListJson()
            } returns MockData.getJsonString()

            sortingCurrencyListUseCase(SortingCurrencyListUseCase.Params(isAscending = false))
                .onEach { result ->
                    result.shouldBeTypeOf<Result.Success<List<CurrencyInfo>>>()
                    result.data.size.shouldBe(14)
                    result.data.first().shouldNotBeNull()
                    result.data.first().symbol.shouldBe("XRP")
                    result.data.last().symbol.shouldBe("BCH")
                }
                .launchIn(coroutineRule.CoroutineScope())
        }
    }
}