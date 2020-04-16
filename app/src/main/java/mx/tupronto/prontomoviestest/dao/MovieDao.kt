package mx.tupronto.prontomoviestest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import mx.tupronto.prontomoviestest.model.Movie

@Dao
interface MovieDao {

    @Insert
    fun insert(contact: Movie)

    @Update
    fun update(vararg contact: Movie)

    @Delete
    fun delete(vararg contact: Movie)

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " ORDER BY movie_id")
    fun getOrderedAgenda(): LiveData<List<Movie>>

}