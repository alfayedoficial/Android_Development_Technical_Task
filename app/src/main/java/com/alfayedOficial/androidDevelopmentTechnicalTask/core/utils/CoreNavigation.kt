package com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavGraphBuilder.composableRoute(route: String, content: @Composable () -> Unit) {
    composable(route = route,
        enterTransition = {
            when (initialState.destination.route) {
                route ->
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(TRANSITION_ANIMATION_DURATION)
                    )

                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                route ->
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = tween(TRANSITION_ANIMATION_DURATION)
                    )

                else -> null
            }
        },
        popEnterTransition = {
            when (initialState.destination.route) {
                route ->
                    slideInHorizontally(
                        initialOffsetX = { fullWidth -> -fullWidth },
                        animationSpec = tween(TRANSITION_ANIMATION_DURATION)
                    )

                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                route ->
                    slideOutHorizontally(
                        targetOffsetX = { fullWidth -> fullWidth },
                        animationSpec = tween(TRANSITION_ANIMATION_DURATION)
                    )

                else -> null
            }
        }

    ) { content() }

}
