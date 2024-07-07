package com.alfayedOficial.androidDevelopmentTechnicalTask.domain.mapper

import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.coreLocal.model.MovieEntity


fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        video = video,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        popularity = popularity,
        voteAverage = voteAverage,
        adult = adult,
        voteCount = voteCount
    )
}

fun MovieEntity?.toMovieOrNull(): Movie? {
    return if (this == null){
        return null
    }else{
        Movie(
            id = id,
            overview = overview,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            video = video,
            title = title,
            posterPath = posterPath,
            backdropPath = backdropPath,
            releaseDate = releaseDate,
            popularity = popularity,
            voteAverage = voteAverage,
            adult = adult,
            voteCount = voteCount
        )
    }
}