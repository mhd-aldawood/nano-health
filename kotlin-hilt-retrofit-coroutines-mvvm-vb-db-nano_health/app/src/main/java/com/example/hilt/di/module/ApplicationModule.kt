package com.example.hilt.di.module

import com.example.hilt.BuildConfig
import com.example.hilt.data.api.ApiHelper
import com.example.hilt.data.api.ApiHelperImpl
import com.mindorks.framework.mvvm.data.api.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    // Provides the base URL used for Retrofit
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    // Provides the OkHttpClient with logging interceptor for debug mode
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient.Builder().build()

    // Provides the Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    // Provides the ApiService interface implementation
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
interface Provider {

    // Binds the ApiHelperImpl class to the ApiHelper interface
    @Binds
    @Singleton
    fun provideHelper(apiHelper: ApiHelperImpl): ApiHelper
}
