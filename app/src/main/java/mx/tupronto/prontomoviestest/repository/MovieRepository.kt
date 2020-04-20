package mx.tupronto.prontomoviestest.repository

import androidx.lifecycle.LiveData
import mx.tupronto.prontomoviestest.data.Movie

interface MovieRepository {

    fun findById(id: Int): LiveData<Movie?>?

    fun findAll(): LiveData<MutableList<Movie?>?>?

    fun insert(product: Movie?): Long

    fun delete(product: Movie?): Int

}