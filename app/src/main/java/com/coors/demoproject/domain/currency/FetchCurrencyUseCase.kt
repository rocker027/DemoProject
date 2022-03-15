package com.coors.demoproject.domain.currency

import com.coors.commoncore.result.Result
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.data.currency.CurrencyInfoMapper
import com.coors.demoproject.data.currency.CurrencyRepositoryImpl
import com.coors.demoproject.di.qualifier.IoDispatcher
import com.coors.demoproject.domain.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepositoryImpl,
    private val currencyInfoMapper: CurrencyInfoMapper,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<CurrencyInfo>>(coroutineDispatcher) {

    override fun execute(parameters: Unit) = flow {
        val json = repository.fetchCurrencyListJson()
        val currencyInfoList = currencyInfoMapper.map(json)
        emit(Result.Success(currencyInfoList))
    }
}