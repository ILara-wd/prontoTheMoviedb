package mx.tupronto.prontomoviestest.persistent

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.tupronto.prontomoviestest.dao.MovieDao
import mx.tupronto.prontomoviestest.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao?

    companion object {
        var TEST_MODE = false
        private const val databaseName = "database_pronto"

        private var db: MovieDatabase? = null
        private var dbInstance: MovieDao? = null

        fun getInstance(context: Context): MovieDao? {
            if (dbInstance == null) {
                if (TEST_MODE) {
                    db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
                        .allowMainThreadQueries().build()
                    dbInstance = db?.movieDao()
                } else {
                    db = Room.databaseBuilder(context, MovieDatabase::class.java, databaseName)
                        .build()
                    dbInstance = db?.movieDao()
                }
            }
            return dbInstance
        }

        private fun close() {
            db?.close()
        }
    }
}