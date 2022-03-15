package com.coors.demoproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.coors.commoncore.result.data
import com.coors.commoncore.result.succeeded
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.domain.currency.FetchCurrencyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DemoActivityViewModel @Inject constructor(
    private val fetchCurrencyListUseCase: FetchCurrencyListUseCase
) : ViewModel() {

    private val _currencyListStateFlow = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencyListStateFlow = _currencyListStateFlow.asStateFlow()
    private val _errorShareFlow = MutableSharedFlow<String>()
    val errorShareFlow = _errorShareFlow.asSharedFlow()

    fun onClickLoading() {
        fetchCurrencyListUseCase(Unit)
            .onEach { result ->
                if (result.succeeded) {
                    _currencyListStateFlow.value = result.data ?: emptyList()
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
        // TODO
    }

}