package com.alfayedOficial.androidDevelopmentTechnicalTask.core.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import com.alfayedOficial.androidDevelopmentTechnicalTask.data.mediator.MoviesRemoteMediator
import com.alfayedOficial.androidDevelopmentTechnicalTask.data.repo.MovieRepoImpl
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.repo.MovieRepo
import com.alfayedOficial.coreLocal.dao.MovieDao
import com.alfayedOficial.coreLocal.database.AppDatabase
import com.alfayedOficial.coreLocal.model.MovieEntity
import com.alfayedOficial.coreNetwork.api.ApiService
import com.alfayedOficial.coreNetwork.utilities.annotation.BaseURL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Hilt RepositoryModule @Inject
 * Date 6/7/2024 - 9:23 AM
 */


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule{

    @Singleton
    @Binds
    fun bindMovieRepo(movieRepoImpl: MovieRepoImpl): MovieRepo

}

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object MediatorModule{

    @Singleton
    @Provides
    fun provideMediator(appDatabase: AppDatabase, apiService: ApiService , @BaseURL baseUrl: String): RemoteMediator<Int, MovieEntity>{
        return MoviesRemoteMediator(apiService, appDatabase , baseUrl)
    }

    @Singleton
    @Provides
    fun providePagingConfig() = PagingConfig(pageSize = 20)

    @Singleton
    @Provides
    fun providePager(pagingConfig : PagingConfig, moviesRemoteMediator : RemoteMediator<Int, MovieEntity>, movieDao: MovieDao) = Pager(
        config = pagingConfig,
        remoteMediator = moviesRemoteMediator ,
        pagingSourceFactory = { movieDao.pagingSource() }
    )
}