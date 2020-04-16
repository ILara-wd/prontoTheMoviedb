package mx.tupronto.prontomoviestest.ui.movie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tupronto.prontomoviestest.MovieTools
import mx.tupronto.prontomoviestest.R
import mx.tupronto.prontomoviestest.service.MovieConstants
import mx.tupronto.prontomoviestest.service.data.MovieInput

class MovieAdapter(
    private val mActivity: Activity,
    private val items: List<MovieInput>,
    private val listener: (MovieInput, Boolean) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie_item, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = items[position]
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

    class MainViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)
    }

}