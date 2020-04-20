package mx.tupronto.prontomoviestest.di;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mx.tupronto.prontomoviestest.data.MovieDao;
import mx.tupronto.prontomoviestest.repository.MovieDataSource;
import mx.tupronto.prontomoviestest.repository.MovieDatabase;
import mx.tupronto.prontomoviestest.repository.MovieRepository;

@Module
public class RoomModule {

    private MovieDatabase demoDatabase;

    public RoomModule(Application mApplication) {
        demoDatabase = Room.databaseBuilder(mApplication, MovieDatabase.class, "movie-db").build();
    }

    @Singleton
    @Provides
    MovieDatabase providesRoomDatabase() {
        return demoDatabase;
    }

    @Singleton
    @Provides
    MovieDao providesProductDao(MovieDatabase demoDatabase) {
        return demoDatabase.getMovieDao();
    }

    @Singleton
    @Provides
    MovieRepository productRepository(MovieDao productDao) {
        return new MovieDataSource(productDao);
    }

}
