package com.coors.demoproject.ui.testcompose

import androidx.lifecycle.ViewModel
import com.coors.commoncore.model.AnchorModel
import com.coors.commoncore.model.AnchorWrapper
import com.coors.demoproject.domain.demo.GetAnchorsUseCase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class TestComposeViewModel @Inject constructor(
    private val getAnchorsUseCase: GetAnchorsUseCase
) : ViewModel() {

//    private val _anchorsStateFlow = MutableStateFlow<List<AnchorModel>>(emptyList())
//    val anchorsStateFlow = _anchorsStateFlow.asStateFlow()

    fun getAnchors(): Flow<List<AnchorModel>> = getAnchorsUseCase(Unit)
        .flatMapLatest {
            if (it is com.coors.commoncore.result.Result.Success) {
                flowOf(it.data)
            } else {
                emptyFlow()
            }
        }
}