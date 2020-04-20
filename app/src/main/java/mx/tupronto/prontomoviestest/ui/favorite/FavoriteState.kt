package mx.tupronto.prontomoviestest.ui.favorite

import mx.tupronto.prontomoviestest.data.Movie

sealed class FavoriteState {
    class ShowMoviesFavorite(val moviesList: MutableList<Movie>) : FavoriteState()
    class RemoveMovie(val movie: Movie) : FavoriteState()
}