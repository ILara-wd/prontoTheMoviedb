package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.data.APIError
import mx.tupronto.prontomoviestest.service.data.MovieInput

interface ImplMovieInteract {
    fun getResponseData(data: List<MovieInput>?)
    fun trowError(error: APIError)
}