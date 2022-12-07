package com.coors.demoproject.di.module

import com.coors.commoncore.data.mapper.Mapper
import com.coors.commoncore.network.api.DemoApiService
import com.coors.demoproject.data.currency.*
import com.coors.demoproject.data.demo.DemoRepository
import com.coors.demoproject.data.demo.DemoRepositoryImpl
import com.coors.demoproject.data.home.HomeRepository
import com.coors.demoproject.data.home.HomeRepositoryImpl
import com.coors.demoproject.di.qualifier.CurrencyMapperQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideCurrencyRepository(): CurrencyRepository = CurrencyRepositoryImpl()

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository = HomeRepositoryImpl()

    @Provides
    @Singleton
    fun provideDemoRepository(
        demoApiService: DemoApiService
    ): DemoRepository =
        DemoRepositoryImpl(demoApiService)

    @Provides
    @CurrencyMapperQualifier
    @Singleton
    fun provideCurrencyMapper(
        moshi: Moshi
    ): Mapper<String, List<CurrencyInfo>> = CurrencyInfoMapper(moshi)
}