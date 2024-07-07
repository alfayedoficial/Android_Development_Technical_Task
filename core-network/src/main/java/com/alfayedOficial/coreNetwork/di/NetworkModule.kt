package com.alfayedOficial.coreNetwork.di

import com.alfayedOficial.coreNetwork.api.ApiService
import com.alfayedOficial.coreNetwork.utilities.NetworkConstants.ACCESS_TOKEN
import com.alfayedOficial.coreNetwork.utilities.NetworkConstants.REQUEST_TIME
import com.alfayedOficial.coreNetwork.utilities.annotation.BaseURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHeadersInterceptor() = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", ACCESS_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(REQUEST_TIME, TimeUnit.MINUTES)
            .connectTimeout(REQUEST_TIME, TimeUnit.MINUTES)
            .addInterceptor(headersInterceptor)
            .addNetworkInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun getDynamicRetrofitClient(
        okHttpClient: OkHttpClient,
        @BaseURL baseUrl: String
    ): Retrofit {


        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
