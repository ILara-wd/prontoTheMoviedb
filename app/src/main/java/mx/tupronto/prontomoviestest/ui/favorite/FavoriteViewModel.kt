package mx.tupronto.prontomoviestest.ui.favorite

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.repository.MovieRepository
import mx.tupronto.prontomoviestest.utility.observeOnce

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movies: LiveData<MutableList<Movie?>?>? = findMovies()

    private lateinit var _favoriteState: MutableLiveData<FavoriteState>

    val favoriteState: LiveData<FavoriteState>
        get() {
            if (!::_favoriteState.isInitialized) {
                _favoriteState = MutableLiveData()
            }
            return _favoriteState
        }

    private fun findMovies(): LiveData<MutableList<Movie?>?>? {
        return movieRepository.findAll()
    }

    fun getMoviesFavorite() {
        val listNoEmpty = mutableListOf<Movie>()
        movies?.observeOnce { list ->
            list?.forEach {
                if (it != null) {
                    listNoEmpty.add(it)
                }
            }
            showData(listNoEmpty)
        }
    }

    private fun showData(listMovie: MutableList<Movie>) {
        _favoriteState.value = FavoriteState.ShowMoviesFavorite(listMovie)
    }

    fun removeFavorite(movie: Movie) {
        AsyncTask.execute {
            movieRepository.delete(movie)
        }
        _favoriteState.value = FavoriteState.RemoveMovie(movie)
    }

}

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(movieRepository) as T
    }
}