package com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model

data class Movie(
    val id: Int?,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = null,
    val voteAverage: Double? = null,
    val adult: Boolean? = null,
    val voteCount: Int? = null

){
    fun getImagePath(): String =
        if (posterPath != null) {
            "https://image.tmdb.org/t/p/w500$posterPath"
        } else {
            "https://i.stack.imgur.com/GNhx0.png"
        }
}
