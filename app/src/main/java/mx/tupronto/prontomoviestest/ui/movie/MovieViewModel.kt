package mx.tupronto.prontomoviestest.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.ScreenState
import mx.tupronto.prontomoviestest.service.data.Movie
import mx.tupronto.prontomoviestest.service.model.APIError

class MovieViewModel(private val movieInteract: MovieInteract) : ViewModel(), ImplMovieInteract {

    private lateinit var _movieState: MutableLiveData<ScreenState<MovieState>>

    val movieState: LiveData<ScreenState<MovieState>>
        get() {
            if (!::_movieState.isInitialized) {
                _movieState = MutableLiveData()
                _movieState.value = ScreenState.Loading
                movieInteract.getMovies(this)
            }
            return _movieState
        }

    override fun getResponseData(data: List<Movie>?) {
        _movieState.value = ScreenState.Render(MovieState.ShowItems(data.orEmpty()))
    }

    override fun trowError(error: APIError) {
        _movieState.value =
            ScreenState.Render(MovieState.ShowMessage(error.status_message.toString()))
    }

    fun onItemClicked(movie: Movie, isAddFavorite: Boolean) {
        if (isAddFavorite) {
            _movieState.value = ScreenState.Render(MovieState.AddFavorite(movie))
        } else {
            _movieState.value = ScreenState.Render(MovieState.RemoveFavorite(movie))
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val mainInteract: MovieInteract) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mainInteract) as T
    }
}