package mx.tupronto.prontomoviestest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tupronto.prontomoviestest.MainActivity
import mx.tupronto.prontomoviestest.R
import mx.tupronto.prontomoviestest.data.Movie

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var rvFav: RecyclerView
    private lateinit var favProgress: ProgressBar
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoriteViewModel = ViewModelProviders.of(
            this,
            FavoriteViewModelFactory((activity as MainActivity).movieRepository)
        )[FavoriteViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        rvFav = root.findViewById(R.id.rv_fav)
        favProgress = root.findViewById(R.id.fav_progress)

        favoriteViewModel.favoriteState.observe(::getLifecycle, ::updateUI)

        favoriteViewModel.getMoviesFavorite()

        return root
    }

    private fun updateUI(renderState: FavoriteState) {
        when (renderState) {
            is FavoriteState.ShowMoviesFavorite -> setItems(renderState.moviesList)
            is FavoriteState.RemoveMovie -> removeMovie(renderState.movie)
        }
    }

    private fun removeMovie(movie: Movie) {
        adapter.removeItem(movie)
    }

    private fun setItems(movies: MutableList<Movie>?) {
        rvFav.layoutManager = GridLayoutManager(activity, 2)
        adapter = FavoriteAdapter(
            requireActivity(),
            movies.orEmpty() as MutableList<Movie>,
            this::onClickItem
        )
        rvFav.adapter = adapter
    }

    private fun onClickItem(movie: Movie) {
        favoriteViewModel.removeFavorite(movie)
    }

}
