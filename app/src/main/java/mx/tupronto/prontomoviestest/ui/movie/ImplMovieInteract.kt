package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.data.Movie
import mx.tupronto.prontomoviestest.service.model.APIError

interface ImplMovieInteract {
    fun getResponseData(data: List<Movie>?)
    fun trowError(error: APIError)
}