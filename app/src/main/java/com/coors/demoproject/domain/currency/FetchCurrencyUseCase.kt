package com.coors.demoproject.domain.currency

import com.coors.commoncore.result.Result
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.data.currency.CurrencyRepository
import com.coors.demoproject.data.currency.Mapper
import com.coors.demoproject.di.qualifier.CurrencyMapperQualifier
import com.coors.demoproject.di.qualifier.IoDispatcher
import com.coors.demoproject.domain.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FetchCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    @CurrencyMapperQualifier private val currencyInfoMapper: Mapper<String, List<CurrencyInfo>>,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<CurrencyInfo>>(coroutineDispatcher) {

    override fun execute(parameters: Unit) = flow {
        val json = repository.fetchCurrencyListJson()
        val currencyInfoList = currencyInfoMapper.map(json)
        emit(Result.Success(currencyInfoList))
    }
}