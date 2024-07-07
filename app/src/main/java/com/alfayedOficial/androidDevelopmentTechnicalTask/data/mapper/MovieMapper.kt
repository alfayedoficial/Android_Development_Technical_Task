package com.alfayedOficial.androidDevelopmentTechnicalTask.data.mapper

import com.alfayedOficial.coreLocal.model.MovieEntity
import com.alfayedOficial.coreNetwork.model.MovieDto

fun MovieDto.toMovieEntity(page: Int): MovieEntity {
    return MovieEntity(
        id = id,
        overview = overview,
        originalLanguage = original_language,
        originalTitle = original_title,
        video = video,
        title = title,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        releaseDate = release_date,
        popularity = popularity,
        voteAverage = vote_average,
        adult = adult,
        voteCount = vote_count,
        page = page
    )
}
