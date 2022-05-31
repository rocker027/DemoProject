package com.coors.demoproject.domain.home

import com.coors.commoncore.domain.FlowUseCase
import com.coors.commoncore.result.Result
import com.coors.demoproject.data.home.HomeMenu
import com.coors.demoproject.data.home.HomeRepository
import com.coors.demoproject.di.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeMenuUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<HomeMenu>>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<HomeMenu>>> = flow {
        Result.Success(homeRepository.getMenu())
    }
}