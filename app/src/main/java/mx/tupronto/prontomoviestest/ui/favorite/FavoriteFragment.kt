package mx.tupronto.prontomoviestest.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        favoriteViewModel.getMovies()?.observe(requireActivity(), updateUI())
        return root
    }

    private fun updateUI(): Observer<in MutableList<Movie?>?> {
        return Observer<MutableList<Movie?>?> { it ->
            val listNoEmpty = mutableListOf<Movie>()
            it?.forEach {
                if (it != null) {
                    listNoEmpty.add(it)
                }
            }
            setItems(listNoEmpty)
        }
    }

    private fun setItems(movies: MutableList<Movie>?) {
        rvFav.layoutManager = GridLayoutManager(activity, 2)
        rvFav.adapter = FavoriteAdapter(requireActivity(), movies.orEmpty(), this::onClickItem)
    }

    private fun onClickItem(movie: Movie) {
        favoriteViewModel.removeFavorite(movie)
    }

}
