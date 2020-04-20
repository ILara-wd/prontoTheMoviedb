package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.data.APIError
import mx.tupronto.prontomoviestest.service.data.MovieInput

interface ImplMovieInteract {
    fun getResponseData(dataWS: MutableList<MovieInput>?, isFirstPage: Boolean)
    fun trowError(error: APIError)
}