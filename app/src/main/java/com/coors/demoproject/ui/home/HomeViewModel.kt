package com.coors.demoproject.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.coors.commoncore.result.Result
import com.coors.demoproject.data.home.HomeMenu
import com.coors.demoproject.data.home.HomeRepository
import com.coors.demoproject.domain.home.GetHomeMenuUseCase
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val getHomeMenuUseCase: GetHomeMenuUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMenus(): Flow<List<HomeMenu>> = getHomeMenuUseCase(Unit)
        .flatMapLatest { result ->
            if (result is Result.Success) {
                flowOf(result.data)
            } else {
                emptyFlow()
            }
        }

}

