package com.alfayedOficial.androidDevelopmentTechnicalTask.data.fakeData

import com.alfayedOficial.coreLocal.model.MovieEntity
import com.alfayedOficial.coreNetwork.model.MovieDto

object DataFake {

    private val _fakeMovieDto = MovieDto(
        id = 323,
        overview = "Test OverView",
        original_language = null,
        original_title = null,
        video = null,
        title = "Title",
        popularity = null,
        adult = null,
    )

    private val _fakeDataMoviesDtoList = listOf(
        _fakeMovieDto,
        _fakeMovieDto,
        _fakeMovieDto,
        _fakeMovieDto,
        _fakeMovieDto
    )
    val fakeDataMoviesDtoList = _fakeDataMoviesDtoList


    private val _fakeMovieEntity = MovieEntity(
        primaryKey = 4384,
        id = 323,
        overview = "Test OverView",
        originalLanguage = null,
        originalTitle = null,
        video = null,
        title = "Title",
        posterPath = null,
        backdropPath = null,
        releaseDate = null,
        popularity = null,
        voteAverage = null,
        adult = null,
        voteCount = null,
        page = 3077
    )
    val fakeMovieEntity = _fakeMovieEntity
}