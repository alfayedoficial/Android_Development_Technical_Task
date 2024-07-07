package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.LocalMovies
import androidx.compose.ui.graphics.vector.ImageVector
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.ScreenItem

/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 5:18 PM
 * Last Modified: 06/07/2024 - 5:18 PM
 */
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    // for home
    object Movie : BottomBarScreen(
        route = ScreenItem.MoviesScreen.route,
        title = "Movie",
        icon = Icons.Filled.LocalMovies
    )

    // for ticket
    object Ticket : BottomBarScreen(
        route = ScreenItem.TicketScreen.route,
        title = "Ticket",
        icon = Icons.Filled.LocalActivity
    )

}