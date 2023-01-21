package com.coors.demoproject.ui.testcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coors.commoncore.model.AnchorModel
import com.coors.demoproject.domain.demo.GetAnchorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.coors.commoncore.result.Result

@HiltViewModel
class TestComposeViewModel @Inject constructor(
    private val getAnchorsUseCase: GetAnchorsUseCase
) : ViewModel() {

    private val _anchorsStateFlow = MutableStateFlow<List<AnchorModel>>(emptyList())
    val anchorsStateFlow = _anchorsStateFlow.asStateFlow()

    fun getAnchors(): Flow<List<AnchorModel>> = getAnchorsUseCase(Unit)
        .flatMapLatest {
            if (it is Result.Success) {
                flowOf(it.data)
            } else {
                emptyFlow()
            }
        }

    init {
        getAnchorsV2()
    }

    private fun getAnchorsV2() {
        getAnchorsUseCase(Unit)
            .onEach {
                _anchorsStateFlow.value = if (it is Result.Success) {
                    it.data
                } else {
                    emptyList()
                }
            }.launchIn(viewModelScope)
    }
}