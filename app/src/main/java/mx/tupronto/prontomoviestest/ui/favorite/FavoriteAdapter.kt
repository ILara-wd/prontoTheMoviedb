package mx.tupronto.prontomoviestest.ui.favorite

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tupronto.prontomoviestest.MovieTools
import mx.tupronto.prontomoviestest.R
import mx.tupronto.prontomoviestest.model.Movie
import mx.tupronto.prontomoviestest.service.MovieConstants

class FavoriteAdapter(
    private val mActivity: Activity,
    private val items: List<Movie>,
    private val removeFavorite: (Movie) -> Unit
) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie_item, parent, false)
        return FavoriteViewHolder(v)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val movie = items[position]

        holder.tvTitle.text = movie.title

        holder.ivFavorite.setImageResource(R.drawable.ic_favorite_red)
        holder.ivFavorite.contentDescription =
            mActivity.getString(R.string.content_description_selected)
        holder.ivFavorite.setOnClickListener {
            changeIconFavorite(holder.ivFavorite)
            removeFavorite(movie)
        }

        MovieTools().showImageByUrl(
            MovieConstants.IMAGE_URL + movie.posterPath,
            holder.ivMoviePoster,
            mActivity
        )

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

    class FavoriteViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)
    }

}