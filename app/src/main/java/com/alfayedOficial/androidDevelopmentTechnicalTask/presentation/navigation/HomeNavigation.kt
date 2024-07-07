package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils.composableRoute
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movies.ui.MoviesScreen
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.ticket.TicketScreen
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.DividerColor
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.divider_thickness
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_4_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movies.viewModel.MoviesViewModel
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.components.AddItem
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.model.BottomBarScreen

@Composable
fun HomeNavigation(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
) {

    val navController = rememberNavController()
    Scaffold(
        containerColor = MainColor,
        bottomBar = { BottomBar(navController = navController) },
        modifier = modifier
    ) { paddingValues ->
        BottomNavGraph(
            navController = navController,
            mainNavController = mainNavController,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }

}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    mainNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = ScreenItem.MoviesScreen.route) {
        composableRoute(route = ScreenItem.MoviesScreen.route, content = {
            val viewModel: MoviesViewModel = hiltViewModel()
            MoviesScreen(viewModel = viewModel, onDetailsClicked = {
                mainNavController.navigate(ScreenItem.MovieDetailsScreen.route.replace("{id}", it.toString()))
            })
        })

        composableRoute(route = ScreenItem.TicketScreen.route, content = {
            TicketScreen()
        })
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    ConstraintLayout(
        modifier = modifier
            .background(color = MainColor)
            .fillMaxWidth()
    ) {
        val (movie, ticket, divider) = createRefs()

        HorizontalDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    top.linkTo(parent.top)
                },
            thickness = divider_thickness,
            color = DividerColor
        )

        AddItem(
            screen = BottomBarScreen.Ticket,
            currentDestination = currentDestination,
            navController = navController,
            modifier = Modifier
                .constrainAs(movie) {
                    start.linkTo(parent.start, spacing_2_2)
                    end.linkTo(ticket.start)
                    linkTo(
                        top = parent.top,
                        topMargin = spacing_4_2,
                        bottomMargin = spacing_2,
                        bottom = parent.bottom
                    )
                }
        )

        AddItem(
            screen = BottomBarScreen.Movie,
            currentDestination = currentDestination,
            navController = navController,
            modifier = Modifier.constrainAs(ticket) {
                start.linkTo(movie.end, spacing_2_2)
                end.linkTo(parent.end)
                linkTo(
                    top = parent.top,
                    topMargin = spacing_4_2,
                    bottomMargin = spacing_2,
                    bottom = parent.bottom
                )
            }
        )
    }
}



