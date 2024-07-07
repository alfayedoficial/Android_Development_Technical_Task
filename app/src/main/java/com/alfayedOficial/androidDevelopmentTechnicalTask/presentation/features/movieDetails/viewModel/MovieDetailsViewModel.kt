package com.alfayedOficial.androidDevelopmentTechnicalTask.presentation.features.movieDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
) : ViewModel() {

    val selectedMovieDetailsState: StateFlow<Movie?>
        get() = _selectedMovieDetailsState
    private val _selectedMovieDetailsState = MutableStateFlow<Movie?>(null)


    fun selectMovieDetails(id: Int) {
        viewModelScope.launch {
            _selectedMovieDetailsState.value = movieRepo.getMovieDetailsById(id)
        }
    }


}