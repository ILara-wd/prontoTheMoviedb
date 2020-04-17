package mx.tupronto.prontomoviestest.ui.favorite

import mx.tupronto.prontomoviestest.model.Movie

sealed class FavoriteState {
    object RemoveFavorite : FavoriteState()
    class ShowMovies(val movies: List<Movie>) : FavoriteState()
}