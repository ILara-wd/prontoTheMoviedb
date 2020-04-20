package mx.tupronto.prontomoviestest.ui.movie

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.repository.MovieRepository
import mx.tupronto.prontomoviestest.service.data.APIError
import mx.tupronto.prontomoviestest.service.data.MovieInput
import mx.tupronto.prontomoviestest.utility.ScreenState
import mx.tupronto.prontomoviestest.utility.observeOnce

class MovieViewModel(
    private val movieInteract: MovieInteract,
    private val movieRepository: MovieRepository
) : ViewModel(), ImplMovieInteract {

    private lateinit var _movieState: MutableLiveData<ScreenState<MovieState>>

    val movieState: LiveData<ScreenState<MovieState>>
        get() {
            if (!::_movieState.isInitialized) {
                _movieState = MutableLiveData()
                _movieState.value = ScreenState.Loading
                movieInteract.setInterfaceInteract(this)
            }
            return _movieState
        }

    override fun getResponseData(dataWS: MutableList<MovieInput>?, isFirstPage: Boolean) {
        val dataLis = mutableListOf<MovieInput>()
        movieRepository.findAll()?.observeOnce { itList ->
            dataWS.orEmpty().forEach {
                val movieInput = it
                movieInput.isFavorite = compareData(it, itList)
                dataLis.add(movieInput)
            }
            _movieState.value = ScreenState.Render(MovieState.ShowItems(dataLis, isFirstPage))
        }
    }

    private fun compareData(movie: MovieInput?, dataWS: MutableList<Movie?>?): Boolean {
        var exist = false
        dataWS.orEmpty().forEach {
            exist = it?.id == movie?.id
            if (exist) {
                return exist
            }
        }
        return exist
    }

    override fun trowError(error: APIError) {
        _movieState.value =
            ScreenState.Render(MovieState.ShowMessage(error.status_message.toString()))
    }

    fun onItemClicked(movieInput: MovieInput, isAddFavorite: Boolean) {
        val movie = Movie(movieInput)
        if (isAddFavorite) {
            AsyncTask.execute {
                movieRepository.insert(movie)
            }
            _movieState.value = ScreenState.Render(MovieState.AddFavorite(movieInput))
        } else {
            AsyncTask.execute {
                movieRepository.delete(movie)
            }
            _movieState.value = ScreenState.Render(MovieState.RemoveFavorite(movieInput))
        }
    }

    fun getMovies(page: Int) {
        movieInteract.getMovies(page)
    }

}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val mainInteract: MovieInteract,
    private val movieRepository: MovieRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mainInteract, movieRepository) as T
    }
}