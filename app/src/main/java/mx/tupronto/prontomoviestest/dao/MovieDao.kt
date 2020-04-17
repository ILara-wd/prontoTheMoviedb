package mx.tupronto.prontomoviestest.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import mx.tupronto.prontomoviestest.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie)

    @Update
    fun update(vararg movie: Movie)

    @Delete
    fun delete(vararg movie: Movie)

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " ORDER BY id")
    fun getMoviesData(): LiveData<List<Movie>>

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " WHERE id=:idMovie")
    fun getMovie(idMovie: Int): LiveData<Movie>

    @Query("DELETE FROM " + Movie.TABLE_NAME)
    fun flushMovieData()

    @Query("SELECT count(*) FROM " + Movie.TABLE_NAME)
    fun getMoviesCount(): Int

}