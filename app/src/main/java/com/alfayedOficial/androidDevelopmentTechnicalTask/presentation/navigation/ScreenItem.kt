package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation


sealed class ScreenItem(val route: String) {
    data object SplashScreen : ScreenItem(route = ScreenType.SPLASH.route)
    data object HomeScreen : ScreenItem(route = ScreenType.HOME.route)
    data object MoviesScreen : ScreenItem(route = ScreenType.MOVIES.route)
    data object TicketScreen : ScreenItem(route = ScreenType.TICKET.route)
    data object MovieDetailsScreen : ScreenItem(route = ScreenType.MOVIE_DETAILS.route)
}

enum class ScreenType(val route: String) {
    SPLASH("splash"),
    HOME("home"),
    MOVIES("movies"),
    TICKET("ticket"),
    MOVIE_DETAILS("movie_details/{id}")
}


