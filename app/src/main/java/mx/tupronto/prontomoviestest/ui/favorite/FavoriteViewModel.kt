package mx.tupronto.prontomoviestest.ui.favorite

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.repository.MovieRepository

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movies: LiveData<MutableList<Movie?>?>? = findMovies()

    private fun findMovies(): LiveData<MutableList<Movie?>?>? {
        return movieRepository.findAll()
    }

    fun getMovies(): LiveData<MutableList<Movie?>?>? {
        return movies
    }

    fun removeFavorite(movie: Movie) {
        AsyncTask.execute {
            movieRepository.delete(movie)
        }
    }

}

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteViewModel(movieRepository) as T
    }
}