package mx.tupronto.prontomoviestest.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.ScreenState
import mx.tupronto.prontomoviestest.model.Movie
import mx.tupronto.prontomoviestest.repository.MovieRepository

class FavoriteViewModel(private val application: Application) : ViewModel() {

    private lateinit var repository: MovieRepository
    private lateinit var _movieState: MutableLiveData<ScreenState<FavoriteState>>

    val favoriteState: LiveData<ScreenState<FavoriteState>>
        get() {
            if (!::_movieState.isInitialized) {
                _movieState = MutableLiveData()
                _movieState.value = ScreenState.Loading
                repository = MovieRepository(application)
                showFavorite(repository.getMovies().value.orEmpty())
            }
            return _movieState
        }

    private fun showFavorite(movies: List<Movie>) {
        //repository.insert(movie)
        _movieState.value = ScreenState.Render(FavoriteState.ShowMovies(movies))
    }

    fun removeFavorite(movie: Movie) {
        repository.delete(movie)
        _movieState.value =
            ScreenState.Render(FavoriteState.ShowMovies(repository.getMovies().value.orEmpty()))
    }

}

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(application) as T
    }
}