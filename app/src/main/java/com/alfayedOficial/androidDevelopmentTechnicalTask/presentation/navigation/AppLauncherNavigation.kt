package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils.composableRoute
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movieDetails.ui.MovieDetailsScreen
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movieDetails.viewModel.MovieDetailsViewModel
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.splash.ui.SplashScreen

@Composable
fun AppLauncherNavigation() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = MainColor,
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController, startDestination = ScreenItem.SplashScreen.route
        ) {
            composableRoute(route = ScreenItem.SplashScreen.route, content = {
                SplashScreen(onMoveActionRoute = {
                    navController.navigate(ScreenItem.HomeScreen.route) {
                        popUpTo(ScreenItem.SplashScreen.route) { inclusive = true }
                    }
                })

            })

            composableRoute(route = ScreenItem.HomeScreen.route, content = {
                HomeNavigation(modifier = Modifier.fillMaxSize(), navController)
            })

            composable(
                route = ScreenItem.MovieDetailsScreen.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                ),
                content = {
                    val movieId = it.arguments?.getInt("id")
                    val viewModel: MovieDetailsViewModel = hiltViewModel()
                    viewModel.selectMovieDetails(movieId!!)
                    MovieDetailsScreen(modifier = Modifier.fillMaxSize(), navController,viewModel)
                }
            )


        }
    }


}

