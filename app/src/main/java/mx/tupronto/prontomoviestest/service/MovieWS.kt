@file:Suppress("BlockingMethodInNonBlockingContext")

package mx.tupronto.prontomoviestest.service

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mx.tupronto.prontomoviestest.service.data.MovieGenreInput
import mx.tupronto.prontomoviestest.service.data.MovieGenreOutput
import mx.tupronto.prontomoviestest.service.model.APIError

object MovieWS {

    fun getMovies(
        data: MovieGenreOutput,
        handler: (error: APIError?, response: MovieGenreInput?) -> Unit
    ) {
        val movieApi = ApiFactory.movieApi

        GlobalScope.launch(Dispatchers.Main) {
            try {

                val request = movieApi.getMoviesByGenreAsync(
                    data.page.toString(),
                    data.with_genres.toString()
                )
                val response = request.await()

                if (!response.isSuccessful) {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), APIError::class.java)
                    handler(error, null)
                    return@launch
                }

                val body: MovieGenreInput? = response.body()

                if (body?.total_results.toString().toInt() < 0) {
                    handler(APIError(678, "no movies available", false), null)
                    return@launch
                }

                handler(null, body)

            } catch (e: java.lang.Exception) {
                handler(APIError(e.hashCode(), e.localizedMessage, false), null)
            }
        }

    }

}