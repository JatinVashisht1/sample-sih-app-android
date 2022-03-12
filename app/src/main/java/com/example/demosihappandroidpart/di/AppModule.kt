package com.example.demosihappandroidpart.di

import com.example.demosihappandroidpart.core.Constants
import com.example.demosihappandroidpart.data.remote.SihApi
import com.example.demosihappandroidpart.data.remote.repository.SihRepoImpl
import com.example.demosihappandroidpart.domain.repository.SihRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesSihApi() : SihApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SihApi::class.java)

    @Provides
    @Singleton
    fun providesSihRepo(api: SihApi) : SihRepo = SihRepoImpl(api = api)
}