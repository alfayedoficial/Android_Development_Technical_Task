package com.alfayedOficial.androidDevelopmentTechnicalTask.data.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.mapper.toMovie
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.mapper.toMovieOrNull
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.repo.MovieRepo
import com.alfayedOficial.coreLocal.dao.MovieDao
import com.alfayedOficial.coreLocal.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val pager: Pager<Int, MovieEntity>
) : MovieRepo {

    override fun getPopularMoviesPagingFlow(): Flow<PagingData<Movie>> {
        return pager.flow.map { pagingData -> pagingData.map { it.toMovie() } }
    }


    override suspend fun getMovieDetailsById(id: Int): Movie? {
        return movieDao.getMovieDetailsById(id).toMovieOrNull()
    }


}