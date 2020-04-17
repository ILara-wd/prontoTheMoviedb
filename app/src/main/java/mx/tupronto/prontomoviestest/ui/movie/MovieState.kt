package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.data.MovieInput

sealed class MovieState {
    class ShowItems(val movies: MutableList<MovieInput>?, val isFirstPage: Boolean) : MovieState()
    class AddFavorite(val movie: MovieInput) : MovieState()
    class RemoveFavorite(val movie: MovieInput) : MovieState()
    class ShowMessage(val message: String) : MovieState()
}