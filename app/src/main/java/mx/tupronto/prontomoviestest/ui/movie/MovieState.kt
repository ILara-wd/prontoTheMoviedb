package mx.tupronto.prontomoviestest.ui.movie

import mx.tupronto.prontomoviestest.service.data.Movie

sealed class MovieState {
    class ShowItems(val movies: List<Movie>) : MovieState()
    class AddFavorite(val movie: Movie) : MovieState()
    class RemoveFavorite(val movie: Movie) : MovieState()
    class ShowMessage(val message: String) : MovieState()
}