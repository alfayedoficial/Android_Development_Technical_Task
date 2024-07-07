package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.ClearBlue
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.Blue
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.BlueBetween
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_10
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.navigation.model.BottomBarScreen

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) {
            Brush.horizontalGradient(
                colors = listOf(
                    Blue,
                    BlueBetween,
                    ClearBlue
                ),
                startX = 0f,
                endX = Float.POSITIVE_INFINITY
            )
        } else {
            Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Transparent
                )
            )
        }

    val contentColor =
        if (selected) Color.White else Color.Gray

    ConstraintLayout(
        modifier = modifier
            .height(view_10)
            .clip(CircleShape)
            .background(background)
            .padding(
                start = spacing_2_2,
                end = spacing_2_2,
                top = spacing_2,
                bottom = spacing_2
            )
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        val (icon, text) = createRefs()
        Icon(
            imageVector = screen.icon,
            contentDescription = "icon",
            tint = contentColor,
            modifier = Modifier.constrainAs(icon) {
                linkTo(
                    start = parent.start,
                    end = text.start
                )
            }
        )
        AnimatedVisibility(
            visible = selected,
            modifier = Modifier.constrainAs(text) {
                linkTo(
                    start = icon.end,
                    startMargin = spacing_2,
                    end = parent.end
                )
                linkTo(
                    top = parent.top,
                    bottom = parent.bottom
                )
            }
        ) {
            Text(
                text = screen.title,
                color = contentColor
            )
        }
    }
}