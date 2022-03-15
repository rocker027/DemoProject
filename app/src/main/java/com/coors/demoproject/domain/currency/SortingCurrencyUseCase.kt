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


class SortingCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepositoryImpl,
    private val currencyInfoMapper: CurrencyInfoMapper,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<SortingCurrencyListUseCase.Params, List<CurrencyInfo>>(coroutineDispatcher) {

    override fun execute(parameters: Params) = flow {
        val json = repository.fetchCurrencyListJson()
        val currencyInfoList = currencyInfoMapper.map(json)
        val afterSortList: List<CurrencyInfo> = if (parameters.isAscending) {
            currencyInfoList.sortedBy { it.symbol }
        } else {
            currencyInfoList.sortedByDescending { it.symbol }
        }
        emit(Result.Success(afterSortList))
    }

    data class Params(val isAscending: Boolean)
}