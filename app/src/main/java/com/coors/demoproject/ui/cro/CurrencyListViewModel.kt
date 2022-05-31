package com.coors.demoproject.ui.cro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coors.commoncore.result.data
import com.coors.commoncore.result.succeeded
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.domain.currency.FetchCurrencyListUseCase
import com.coors.demoproject.domain.currency.SortingCurrencyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val fetchCurrencyListUseCase: FetchCurrencyListUseCase,
    private val sortingCurrencyListUseCase: SortingCurrencyListUseCase
) : ViewModel() {

    private val _currencyListStateFlow = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencyListStateFlow = _currencyListStateFlow.asStateFlow()

    private val _errorShareFlow = MutableSharedFlow<String>()
    val errorShareFlow = _errorShareFlow.asSharedFlow()

    // cache sort flag toggle asc or descending by symbol
    private val _isAscendingStateFlow = MutableStateFlow<Boolean>(false)

    fun onClickLoading() {
        fetchCurrencyListUseCase(Unit)
            .onEach { result ->
                if (result.succeeded) {
                    _currencyListStateFlow.value = result.data ?: emptyList()
                    resetSort()
                }
            }
            .catch { throwable ->
                throwable.message?.let {
                    _errorShareFlow.emit(it)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onClickSorting() {
        val toggleSort = !_isAscendingStateFlow.value
        sortingCurrencyListUseCase(
            SortingCurrencyListUseCase.Params(isAscending = toggleSort)
        )
            .onEach { result ->
                if (result.succeeded) {
                    _currencyListStateFlow.value = result.data ?: emptyList()
                    updateSort(toggleSort)
                }
            }
            .catch { throwable ->
                throwable.message?.let {
                    _errorShareFlow.emit(it)
                }
            }
            .launchIn(viewModelScope)
    }


    // reset sort flag
    private fun resetSort() {
        _isAscendingStateFlow.value = false
    }

    // update new sort flag
    private fun updateSort(toggleSort: Boolean) {
        _isAscendingStateFlow.value = toggleSort
    }

}