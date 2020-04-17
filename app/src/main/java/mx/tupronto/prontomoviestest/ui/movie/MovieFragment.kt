package mx.tupronto.prontomoviestest.ui.movie

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
import mx.tupronto.prontomoviestest.service.data.MovieInput

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var rvMovies: RecyclerView
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        movieViewModel = ViewModelProviders.of(
            this,
            MovieViewModelFactory(MovieInteract(), requireActivity().application)
        )[MovieViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        rvMovies = root.findViewById(R.id.rv_movie)
        progress = root.findViewById(R.id.movie_progress)

        movieViewModel.movieState.observe(::getLifecycle, ::updateUI)

        return root
    }

    private fun updateUI(screenState: ScreenState<MovieState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MovieState) {
        hideProgress()
        when (renderState) {
            is MovieState.ShowItems -> setItems(renderState.movies)
            is MovieState.ShowMessage -> showMessage(renderState.message)
            is MovieState.AddFavorite -> addFavorite()
            is MovieState.RemoveFavorite -> removeFavorite()
        }
    }

    private fun addFavorite() {
        Toast.makeText(activity, "Movie Add", Toast.LENGTH_LONG).show()
    }

    private fun removeFavorite() {
        Toast.makeText(activity, "Movie Remove", Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        rvMovies.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        rvMovies.visibility = View.VISIBLE
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun setItems(items: List<MovieInput>) {
        rvMovies.layoutManager = GridLayoutManager(activity, 2)
        rvMovies.adapter = MovieAdapter(requireActivity(), items, movieViewModel::onItemClicked)
    }

}
