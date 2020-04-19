package mx.tupronto.prontomoviestest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.*
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.data.MovieDao
import mx.tupronto.prontomoviestest.repository.MovieDatabase
import mx.tupronto.prontomoviestest.utility.observeOnce
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
open class MovieDaoTest {

    private var noteDAO: MovieDao? = null
    private var db: MovieDatabase? = null

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onCreateDB() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
        noteDAO = db?.getMovieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db?.close()
    }

    @Test
    @Throws(Exception::class)
    fun readAndWriteTests() {
        val movie = Movie()

        movie.id = Random().nextInt()
        movie.title = "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)"
        movie.popularity = Random().nextInt().toDouble()
        movie.voteCount = Random().nextInt()
        movie.video = false
        movie.adult = false
        movie.posterPath = "/y5xG3uLG1kfhidTQEFhJljB7d1r.jpg"
        movie.backdropPath = "/y5xG3uLG1kfhidTQEFhJljB7d1r.jpg"
        movie.originalLanguage = UUID.randomUUID().toString()
        movie.originalTitle = UUID.randomUUID().toString()
        movie.voteAverage = Random().nextInt().toDouble()
        movie.overview = UUID.randomUUID().toString()
        movie.releaseDate = UUID.randomUUID().toString()

        // insert
        val insertedID = noteDAO?.insert(movie)
        assertNotNull(insertedID)

        // find by id
        noteDAO?.findById(insertedID.toString().toInt())?.observeOnce {
            assertNotNull(it)
            assertTrue(it?.title == movie.title)

            // delete
            val deletedQtd = noteDAO?.delete(it)
            assertTrue(deletedQtd == 1)
        }

    }

    @Test
    @Throws(Exception::class)
    fun writePostAndReadInList() {

        val movieList = noteDAO?.findAll()
        assertNotNull(movieList)

        movieList?.observeOnce {
            assertEquals(0, it?.size)
        }

        val movie = Movie()
        movie.id = Random().nextInt()
        movie.title = "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)"
        movie.popularity = Random().nextInt().toDouble()
        movie.voteCount = Random().nextInt()
        movie.video = false
        movie.adult = false
        movie.posterPath = "/y5xG3uLG1kfhidTQEFhJljB7d1r.jpg"
        movie.backdropPath = "/y5xG3uLG1kfhidTQEFhJljB7d1r.jpg"
        movie.originalLanguage = UUID.randomUUID().toString()
        movie.originalTitle = UUID.randomUUID().toString()
        movie.voteAverage = Random().nextInt().toDouble()
        movie.overview = UUID.randomUUID().toString()
        movie.releaseDate = UUID.randomUUID().toString()

        noteDAO?.insert(movie)

        movieList?.observeOnce {
            assertEquals(1, it?.size)
        }

    }

}