package mx.tupronto.prontomoviestest.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.tupronto.prontomoviestest.dao.MovieDao
import mx.tupronto.prontomoviestest.model.Movie
import mx.tupronto.prontomoviestest.persistent.MovieDatabase

class MovieRepository(application: Application) {

    private val movieDao: MovieDao? = MovieDatabase.getInstance(application)

    fun insert(movie: Movie) {
        if (movieDao != null)
            InsertAsyncTask(movieDao).execute(movie)
    }

    fun delete(movie: Movie) {
        if (movieDao != null) DeleteAsyncTask(movieDao).execute(movie)
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao?.getMoviesData() ?: MutableLiveData()
    }

    fun getMovieById(id: Int): LiveData<Movie> {
        return movieDao?.getMovie(id)!!
    }

    private class InsertAsyncTask(private val MovieDao: MovieDao) :
        AsyncTask<Movie, Void, Void>() {
        override fun doInBackground(vararg movies: Movie?): Void? {
            for (movie in movies) {
                if (movie != null) MovieDao.insert(movie)
            }
            return null
        }
    }

    private class DeleteAsyncTask(private val MovieDao: MovieDao) :
        AsyncTask<Movie, Void, Void>() {
        override fun doInBackground(vararg movies: Movie?): Void? {
            for (movie in movies) {
                if (movie != null) MovieDao.delete(movie)
            }
            return null
        }
    }

}