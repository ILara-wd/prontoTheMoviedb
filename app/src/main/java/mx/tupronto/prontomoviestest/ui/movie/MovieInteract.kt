package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.MovieWS
import mx.tupronto.prontomoviestest.service.data.MovieGenreOutput

class MovieInteract {

    fun getMovies(mImplMainInteract: ImplMovieInteract) {

        val data = MovieGenreOutput()
        data.page = 1
        data.with_genres = 28

        MovieWS.getMovies(data) { error, response ->
            if (error != null) {
                mImplMainInteract.trowError(error)
            }
            if (response != null) {
                mImplMainInteract.getResponseData(response.results)
            }
        }

    }

}