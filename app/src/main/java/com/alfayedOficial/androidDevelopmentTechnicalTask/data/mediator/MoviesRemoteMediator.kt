package com.alfayedOficial.androidDevelopmentTechnicalTask.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils.DEFAULT_FIRST_PAGE
import com.alfayedOficial.androidDevelopmentTechnicalTask.data.mapper.toMovieEntity
import com.alfayedOficial.coreLocal.database.AppDatabase
import com.alfayedOficial.coreLocal.model.MovieEntity
import com.alfayedOficial.coreNetwork.api.ApiService
import com.alfayedOficial.coreNetwork.utilities.NetworkLinks
import com.alfayedOficial.coreNetwork.utilities.annotation.BaseURL
import com.alfayedOficial.coreNetwork.utilities.getApiLink
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviesRemoteMediator(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    @BaseURL private val baseURl: String,
) : RemoteMediator<Int, MovieEntity>(){

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> DEFAULT_FIRST_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastPage = appDatabase.movieDao().getLastPage()
                    if (lastPage == 0){
                        DEFAULT_FIRST_PAGE
                    }else{
                        lastPage + DEFAULT_FIRST_PAGE
                    }
                }
            }

            val popularMovesResponse = apiService.getPopularMoviesApi(baseURl.getApiLink(NetworkLinks.GetPopularMovies.type) , page = loadKey)

            appDatabase.withTransaction {
                if ( loadType == LoadType.REFRESH){
                    appDatabase.movieDao().clearAll()
                }

                val movieEntities = popularMovesResponse.results.map { it.toMovieEntity(loadKey) }
                appDatabase.movieDao().upsertAll(movieEntities)
            }

            MediatorResult.Success(endOfPaginationReached = popularMovesResponse.results.isEmpty())
        }catch (e:IOException){
            MediatorResult.Error(e)
        }catch (e:HttpException){
            MediatorResult.Error(e)
        }
    }
}