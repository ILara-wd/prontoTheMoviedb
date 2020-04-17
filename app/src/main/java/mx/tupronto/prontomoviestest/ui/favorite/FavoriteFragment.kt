package mx.tupronto.prontomoviestest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tupronto.prontomoviestest.R
import mx.tupronto.prontomoviestest.ScreenState
import mx.tupronto.prontomoviestest.model.Movie

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var rvFav: RecyclerView
    private lateinit var favProgress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favoriteViewModel = ViewModelProviders.of(
            this,
            FavoriteViewModelFactory(requireActivity().application)
        )[FavoriteViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        rvFav = root.findViewById(R.id.rv_fav)
        favProgress = root.findViewById(R.id.fav_progress)

        favoriteViewModel.favoriteState.observe(::getLifecycle, ::updateUI)

        return root
    }

    private fun updateUI(screenState: ScreenState<FavoriteState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun showProgress() {
        favProgress.visibility = View.VISIBLE
        rvFav.visibility = View.GONE
    }

    private fun hideProgress() {
        favProgress.visibility = View.GONE
        rvFav.visibility = View.VISIBLE
    }

    private fun processRenderState(renderState: FavoriteState) {
        hideProgress()
        when (renderState) {
            is FavoriteState.ShowMovies -> setItems(renderState.movies)
            is FavoriteState.RemoveFavorite -> removeFavorite()
        }
    }

    private fun setItems(movies: List<Movie>) {
        rvFav.layoutManager = GridLayoutManager(activity, 2)
        rvFav.adapter =
            FavoriteAdapter(requireActivity(), movies, favoriteViewModel::removeFavorite)
    }

    private fun removeFavorite() {
        Toast.makeText(activity, "Movie Remove", Toast.LENGTH_LONG).show()
    }

}
