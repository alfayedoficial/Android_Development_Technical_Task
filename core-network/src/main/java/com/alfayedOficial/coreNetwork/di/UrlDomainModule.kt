package com.alfayedOficial.coreNetwork.di

import com.alfayedOficial.coreNetwork.utilities.annotation.BaseURL
import com.alfayedOficial.coreNetwork.utilities.annotation.ImageBaseURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Inject Url Domain
 * Date 24/02/2024 - 2:50 PM
 */

@Module
@InstallIn(SingletonComponent::class)
object UrlDomainModule {


    @BaseURL
    @Provides
    @Singleton
    fun provideBaseURL(): String {
        return "https://api.themoviedb.org"
    }

    @ImageBaseURL
    @Provides
    @Singleton
    fun provideImageBaseURL(): String {
        return "https://image.tmdb.org/t/p/original"
    }
}

