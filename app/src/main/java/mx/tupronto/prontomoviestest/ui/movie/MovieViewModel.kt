package mx.tupronto.prontomoviestest.ui.movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.ScreenState
import mx.tupronto.prontomoviestest.model.Movie
import mx.tupronto.prontomoviestest.repository.MovieRepository
import mx.tupronto.prontomoviestest.service.data.APIError
import mx.tupronto.prontomoviestest.service.data.MovieInput

class MovieViewModel(
    private val movieInteract: MovieInteract,
    private val application: Application
) : ViewModel(), ImplMovieInteract {

    private lateinit var repository: MovieRepository
    private lateinit var _movieState: MutableLiveData<ScreenState<MovieState>>

    val movieState: LiveData<ScreenState<MovieState>>
        get() {
            if (!::_movieState.isInitialized) {
                _movieState = MutableLiveData()
                _movieState.value = ScreenState.Loading
                movieInteract.getMovies(this)
                repository = MovieRepository(application)
            }
            return _movieState
        }

    override fun getResponseData(data: List<MovieInput>?) {
        _movieState.value = ScreenState.Render(MovieState.ShowItems(data.orEmpty()))
    }

    override fun trowError(error: APIError) {
        _movieState.value =
            ScreenState.Render(MovieState.ShowMessage(error.status_message.toString()))
    }

    fun onItemClicked(movieInput: MovieInput, isAddFavorite: Boolean) {

        val movie = Movie(movieInput)

        if (isAddFavorite) {
            repository.insert(movie)
            _movieState.value = ScreenState.Render(MovieState.AddFavorite(movieInput))
        } else {
            repository.delete(movie)
            _movieState.value = ScreenState.Render(MovieState.RemoveFavorite(movieInput))
        }

    }

}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val mainInteract: MovieInteract,
    private val application: Application
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mainInteract, application) as T
    }
}