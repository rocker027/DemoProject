package com.coors.demoproject.domain.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coors.commoncore.result.Result
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
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.junit.Rule

class FetchCurrencyListUseCaseTest : AnnotationSpec() {

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
                    result.shouldBeTypeOf<Result.Success<List<CurrencyInfo>>>()
                    result.data.size.shouldBe(14)
                    result.data.first().shouldNotBeNull()
                    result.data.first().name.shouldBe("Bitcoin")
                    result.data.last().name.shouldBe("USD Coin")
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
                    result.shouldBeTypeOf<Result.Error>()
                    result.exception.message.shouldContain("java.io.EOFException: End of input")
                }
                .launchIn(coroutineRule.CoroutineScope())
        }
    }
}