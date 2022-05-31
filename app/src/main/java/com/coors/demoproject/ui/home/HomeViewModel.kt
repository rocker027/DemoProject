package com.coors.demoproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coors.demoproject.domain.home.GetHomeMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMenuUseCase: GetHomeMenuUseCase
) : ViewModel() {

    init {
        getHomeMenuUseCase(Unit)
            .onEach {

            }.launchIn(viewModelScope)
    }
}

