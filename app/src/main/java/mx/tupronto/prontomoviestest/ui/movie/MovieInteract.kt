package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.MovieWS
import mx.tupronto.prontomoviestest.service.data.MovieGenreOutput
import mx.tupronto.prontomoviestest.service.data.MovieInput

class MovieInteract {

    private lateinit var mImplMainInteract: ImplMovieInteract

    fun setInterfaceInteract(mImplMainInteract: ImplMovieInteract) {
        this.mImplMainInteract = mImplMainInteract
    }

    fun getMovies(page: Int) {
        val data = MovieGenreOutput()
        data.page = page
        data.with_genres = 28

        MovieWS.getMovies(data) { error, response ->
            if (error != null) {
                mImplMainInteract.trowError(error)
            }
            if (response != null) {
                mImplMainInteract.getResponseData(
                    response.results as MutableList<MovieInput>,
                    page == 1
                )
            }
        }
    }

}