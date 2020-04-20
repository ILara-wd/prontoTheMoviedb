package mx.tupronto.prontomoviestest.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import mx.tupronto.prontomoviestest.MainActivity;
import mx.tupronto.prontomoviestest.data.MovieDao;
import mx.tupronto.prontomoviestest.repository.MovieDatabase;
import mx.tupronto.prontomoviestest.repository.MovieRepository;

@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    MovieDao productDao();

    MovieDatabase demoDatabase();

    MovieRepository productRepository();

    Application application();

}
