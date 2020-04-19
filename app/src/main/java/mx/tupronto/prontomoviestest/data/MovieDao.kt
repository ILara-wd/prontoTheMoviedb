package mx.tupronto.prontomoviestest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun findById(id: Int): LiveData<Movie?>?

    @Query("SELECT * FROM Movie")
    fun findAll(): LiveData<List<Movie?>?>?

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie?): Long

    @Delete
    fun delete(movie: Movie?): Int

}