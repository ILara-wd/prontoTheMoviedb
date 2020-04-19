package mx.tupronto.prontomoviestest.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import mx.tupronto.prontomoviestest.data.Movie;
import mx.tupronto.prontomoviestest.data.MovieDao;

public class MovieDataSource implements MovieRepository {

    private MovieDao movieDao;

    @Inject
    public MovieDataSource(MovieDao productDao) {
        this.movieDao = productDao;
    }

    @Override
    public LiveData<Movie> findById(int id) {
        return movieDao.findById(id);
    }

    @Override
    public LiveData<List<Movie>> findAll() {
        return movieDao.findAll();
    }

    @Override
    public long insert(Movie product) {
        return movieDao.insert(product);
    }

    @Override
    public int delete(Movie product) {
        return movieDao.delete(product);
    }

}
