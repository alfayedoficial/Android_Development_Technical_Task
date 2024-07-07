package com.alfayedOficial.coreNetwork.utilities



enum class NetworkLinks(val type: String) {
  /**-----------------------   GET   ---------------------*/
  GetPopularMovies("/3/movie/popular")
}

fun String.getApiLink(endPoint: String) = this.plus(endPoint)
fun String.getImageUrl(imageUrl: String) = this.plus(imageUrl)
