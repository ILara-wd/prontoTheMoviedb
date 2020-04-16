package mx.tupronto.prontomoviestest.service

import kotlinx.coroutines.Deferred
import mx.tupronto.prontomoviestest.service.data.MovieGenreInput
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("discover/movie?sort_by=${MovieConstants.SORT_BY}&include_adult=false&include_video=false&api_key=${MovieConstants.API_KEY}&language=${MovieConstants.LANGUAGE}")
    fun getMoviesByGenreAsync(
        @Query("page") page: String,
        @Query("with_genres") with_genres: String
    )
            : Deferred<Response<MovieGenreInput>>

}