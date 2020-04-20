package mx.tupronto.prontomoviestest.utility

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.tupronto.prontomoviestest.R

class MovieTools {

    fun showImageByUrl(_url: String, mImageView: ImageView, mActivity: Activity) {
        Glide.with(mActivity).load(_url).into(mImageView)
            .apply {
                RequestOptions
                    .placeholderOf(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
            }
    }

}