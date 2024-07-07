package com.alfayedOficial.androidDevelopmentTechnicalTask.domain.repo

import androidx.paging.PagingData
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepo {

    fun getPopularMoviesPagingFlow(): Flow<PagingData<Movie>>

    suspend fun getMovieDetailsById(id:Int): Movie?

}