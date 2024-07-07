package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movieDetails.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.Gold
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.GoldDarker
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.MainColor100
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_1
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_1_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_25
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_2_2
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_3
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_4
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.spacing_6
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_25
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_6
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.ui.theme.view_75
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movieDetails.viewModel.MovieDetailsViewModel
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views.CategoryChip
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.views.MovieTitle
import com.alfayedoficial.androiddevelopmenttechnicaltask.R
import java.math.RoundingMode

/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 5:05 PM
 * Last Modified: 06/07/2024 - 5:05 PM
 */

@Composable
fun MovieDetailsScreen(modifier: Modifier = Modifier,navController: NavController, viewModel: MovieDetailsViewModel) {

    val selectedMovie by viewModel.selectedMovieDetailsState.collectAsState()

    Box(modifier = modifier) {

        if (selectedMovie == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        } else {

            CloseMovie(navController = navController , modifier = Modifier.padding(16.dp).align(Alignment.TopStart).zIndex(2f))

            MoviePosterDetail(path = selectedMovie!!.getImagePath())

            Column(
                modifier
                    .padding(top = 460.dp)
                    .padding(horizontal = 16.dp)) {

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    MovieTitle(
                        title = selectedMovie!!.originalTitle ?:"",
                        style = TextStyle.Default.copy(fontSize = 20.sp , fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp),
                    )

                    VoteMovie(
                        voteAverage = selectedMovie!!.voteAverage ?: 0.0,
                        voteCount = selectedMovie!!.voteCount ?: 0,
                    )
                }

                Text(
                    text = "${selectedMovie!!.overview.orEmpty()} ${selectedMovie!!.overview.orEmpty()}",
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodySmall,
                    color = White,

                    )

            }

        }

    }
}

@Composable
fun CloseMovie(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Filled.Close,
        contentDescription = "Close screen",
        tint = White,
        modifier = modifier
            .clip(CircleShape)
            .background(
                Black.copy(
                    alpha = 0.6f
                )
            )
            .padding(spacing_2)
            .clickable {
                navController.popBackStack()
            }
    )
}

@Composable
fun MoviePosterDetail(
    path: String,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (poster, gradient) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(path)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "movie poster",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(poster) {
                    top.linkTo(parent.top)
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                }
        )
        BlackVerticalGradient(
            size = view_75,
            startColor = MainColor,
            modifier = Modifier.constrainAs(gradient) {
                bottom.linkTo(parent.bottom)
                linkTo(
                    start = parent.start,
                    end = parent.end
                )
                width = Dimension.fillToConstraints
            }
        )
    }
}

@Composable
fun BlackVerticalGradient(
    size: Dp,
    startColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                Brush.verticalGradient(
                    0.0f to startColor,
                    0.5F to startColor,
                    1.0f to Color.Transparent,
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f
                )
            )
    )
}



@Composable
fun VoteMovie(
    voteAverage: Double,
    voteCount: Int,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (icon, voteAverageRef, voteCountRef) = createRefs()
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Star ranking",
            tint = Gold,
            modifier = Modifier
                .size(view_6)
                .constrainAs(icon) {
                    start.linkTo(parent.start, spacing_1)
                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )
        Text(
            text = voteAverage.toBigDecimal().setScale(1, RoundingMode.CEILING).toString(),
            style = MaterialTheme.typography.titleSmall,
            color = Gold,
            modifier = Modifier
                .constrainAs(voteAverageRef) {
                    start.linkTo(icon.end, spacing_1)
                    linkTo(
                        top = icon.top,
                        bottom = icon.bottom
                    )
                }
        )
        Text(
            text = "($voteCount reviews)",
            style = MaterialTheme.typography.displayMedium,
            color = White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(voteCountRef) {
                    linkTo(
                        start = voteAverageRef.end,
                        startMargin = spacing_2,
                        end = parent.end,
                        endMargin = spacing_6
                    )
                    linkTo(
                        top = icon.top,
                        bottom = icon.bottom
                    )
                    width = Dimension.fillToConstraints
                }
        )
    }
}


