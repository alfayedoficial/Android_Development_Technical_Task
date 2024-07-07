package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movies.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_1
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_3
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_4
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_4_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_6
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_25
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_6
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils.TWO_THIRDS_SCREEN
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views.CategoryChip
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views.MoviePoster
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views.MovieTitle
import kotlinx.coroutines.delay

/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 7:13 PM
 * Last Modified: 06/07/2024 - 7:13 PM
 */

@Composable
fun MovieItem(
    movieModel: Movie,
    modifier: Modifier = Modifier,
    goToMovieDetail: (Int) -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing_2_2, vertical = spacing_1),
        onClick = { goToMovieDetail(movieModel.id!!) },
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MoviePoster(
                imagePath = movieModel.getImagePath(),
                size = view_25,
            )

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessVeryLow)),
                exit = fadeOut(animationSpec = spring(stiffness = Spring.StiffnessVeryLow)),
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    MovieText(movieModel = movieModel)

                    Spacer(modifier = Modifier.size(spacing_1))
                   MovieCategories(
                       categories = arrayOf(movieModel.originalLanguage!!).asList(),
                   )
                }
            }
        }
    }
}



@Composable
fun MovieText(
    movieModel: Movie,
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        MovieTitle(
            title = movieModel.originalTitle ?: "",
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.size(spacing_1))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = "time",
                tint = Color.White,
                modifier = Modifier
                    .size(view_6)
            )

            Text(
                text = movieModel.releaseDate.orEmpty(),
                color = Color.White,
            )
        }
    }
}

@Composable
fun MovieCategories(
    categories: List<String>,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(spacing_1)) {

        val firstCategory = categories.firstOrNull()
        CategoryChip(
            category = firstCategory,
        )

        Spacer(modifier = Modifier.size(spacing_1))

        val secondCategory = categories.getOrNull(1)
        if (secondCategory != null) {
            CategoryChip(
                category = secondCategory,
            )
        }

    }
}

