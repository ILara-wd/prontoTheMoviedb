package mx.tupronto.prontomoviestest

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import mx.tupronto.prontomoviestest.data.MovieDao
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("UNCHECKED_CAST")
@RunWith(AndroidJUnit4::class)
class MovieDatabaseReadWriteTest {
    private var movieDao: MovieDao? = null

/*    @Before
    fun setup() {
        MovieDatabase.TEST_MODE = true
        movieDao = MovieDatabase.getInstance(InstrumentationRegistry.getTargetContext())
    }*/

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_Item() {
/*        val movie = Movie(
            12.2,
            1,
            false,
            "/qdwpe2rk2xJ0MwO64ouVTNOyTzD.jpg",
            false,
            "/qdwpe2rk2xJ0MwO64ouVTNOyTzD.jpg",
            "asdasdasdasd",
            "asdasdasdasd",
            "asdasdasdasd",
            12.2,
            "asdasdasdasd",
            "asdasd"
        )
        movieDao?.insert(movie)*/
/*        val movieTest = getValue(movieDao?.getMovie(movie.id)!!)
        Assert.assertEquals(movie.title, movieTest.title)*/
    }

/*    @Test
    fun should_Flush_All_Data(){
        movieDao?.flushMovieData()
        Assert.assertEquals(movieDao?.getMoviesCount(), 0)
    }*/

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T?) {
                data[0] = t
                latch.countDown()
                liveData.removeObserver(this)
            }

        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        return data[0] as T
    }

}