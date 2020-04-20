package mx.tupronto.prontomoviestest.ui.movie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tupronto.prontomoviestest.MainActivity
import mx.tupronto.prontomoviestest.R
import mx.tupronto.prontomoviestest.data.Movie
import mx.tupronto.prontomoviestest.service.MovieConstants
import mx.tupronto.prontomoviestest.service.data.MovieInput
import mx.tupronto.prontomoviestest.utility.MovieTools
import mx.tupronto.prontomoviestest.utility.observeOnce

class MovieAdapter(
    private val mActivity: Activity,
    private val items: MutableList<MovieInput>,
    private val listener: (MovieInput, Boolean) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var repository = (mActivity as MainActivity).movieRepository

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie_item, parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]

        if (movie.isFavorite) {
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_red)
            holder.ivFavorite.contentDescription =
                mActivity.getString(R.string.content_description_selected)
        } else {
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite_border)
            holder.ivFavorite.contentDescription =
                mActivity.getString(R.string.content_description_unselected)
        }

        holder.tvTitle.text = movie.title
        holder.ivFavorite.setOnClickListener {
            val isAddFavorite = changeIconFavorite(holder.ivFavorite)
            listener(movie, isAddFavorite)
        }
        MovieTools().showImageByUrl(
            MovieConstants.IMAGE_URL + movie.poster_path.toString(),
            holder.ivMoviePoster,
            mActivity
        )

    }

    fun addMoviesPaginate(moreMovies: List<MovieInput>) {
        for (item in moreMovies) {
            addMovie(item)
        }
    }

    private fun addMovie(movie: MovieInput) {
        items.add(movie)
        notifyItemInserted(items.size - 1)
    }

    private fun compareMovieFavorite(movie: MovieInput, imageView: ImageView) {

        repository.findById(movie.id.toString().toInt())?.observeOnce {
            if (it == Movie(movie)) {
                imageView.setImageResource(R.drawable.ic_favorite_red)
                imageView.contentDescription =
                    mActivity.getString(R.string.content_description_selected)
            } else {
                imageView.setImageResource(R.drawable.ic_favorite_border)
                imageView.contentDescription =
                    mActivity.getString(R.string.content_description_unselected)
            }
        }

        /*
        if (repository.getMovieById(movie.id.toString().toInt()).value == Movie(
                movie
            )
        ) {
            imageView.setImageResource(R.drawable.ic_favorite_red)
            imageView.contentDescription =
                mActivity.getString(R.string.content_description_selected)
        } else {
            imageView.setImageResource(R.drawable.ic_favorite_border)
            imageView.contentDescription =
                mActivity.getString(R.string.content_description_unselected)
        }*/
    }

    private fun changeIconFavorite(imageView: ImageView): Boolean {
        return if (imageView.contentDescription.toString() == mActivity.getString(R.string.content_description_selected)) {
            imageView.contentDescription =
                mActivity.getString(R.string.content_description_unselected)
            imageView.setImageResource(R.drawable.ic_favorite_border)
            false
        } else {
            imageView.setImageResource(R.drawable.ic_favorite_red)
            imageView.contentDescription =
                mActivity.getString(R.string.content_description_selected)
            true
        }
    }

    override fun getItemCount(): Int = items.size

    class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)
    }

}