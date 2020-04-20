package mx.tupronto.prontomoviestest.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.data.MovieDao

@Database(entities = [Movie::class], version = MovieDatabase.VERSION)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        const val VERSION: Int = 1
    }

    abstract fun getMovieDao(): MovieDao?

}