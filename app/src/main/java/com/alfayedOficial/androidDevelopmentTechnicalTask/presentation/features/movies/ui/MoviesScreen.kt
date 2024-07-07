package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movies.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.alfayedOficial.androidDevelopmentTechnicalTask.core.utils.kuToast
import com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movies.viewModel.MoviesViewModel

/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 5:05 PM
 * Last Modified: 06/07/2024 - 5:05 PM
 */

@Composable
fun MoviesScreen(modifier: Modifier = Modifier,
    onDetailsClicked: (Int) -> Unit,
    viewModel: MoviesViewModel
    ) {
        val context = LocalContext.current
        val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
        val loadState: CombinedLoadStates = popularMovies.loadState

        LaunchedEffect(key1 = loadState) {
            if(loadState.refresh is LoadState.Error) {
                context.kuToast("Error: " + (popularMovies.loadState.refresh as LoadState.Error).error.message)
            }
        }

    Box(modifier = modifier.fillMaxSize()) {

        if (loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        }else{
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = popularMovies.itemCount) { index ->
                    val movie = popularMovies[index]
                    if(movie != null) {
                        MovieItem(
                            movieModel = movie,
                            modifier = Modifier,
                            goToMovieDetail = onDetailsClicked
                        )
                    }
                }
                item {
                    if(popularMovies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}
