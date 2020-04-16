package mx.tupronto.prontomoviestest.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.tupronto.prontomoviestest.dao.MovieDao
import mx.tupronto.prontomoviestest.model.Movie
import mx.tupronto.prontomoviestest.persistent.MovieDatabase


class MovieRepository(application: Application) {

    private val movieDao: MovieDao? = MovieDatabase.getInstance(application)?.movieDao()

    fun insert(Movie: Movie) {
        if (movieDao != null) InsertAsyncTask(movieDao).execute(Movie)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao?.getOrderedAgenda() ?: MutableLiveData()
    }

    private class InsertAsyncTask(private val MovieDao: MovieDao) :
        AsyncTask<Movie, Void, Void>() {
        override fun doInBackground(vararg Movies: Movie?): Void? {
            for (Movie in Movies) {
                if (Movie != null) MovieDao.insert(Movie)
            }
            return null
        }
    }
}