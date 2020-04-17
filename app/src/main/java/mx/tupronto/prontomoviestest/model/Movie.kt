package mx.tupronto.prontomoviestest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.tupronto.prontomoviestest.service.data.MovieInput

@Entity(tableName = Movie.TABLE_NAME)
data class Movie(

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "video")
    val video: Boolean,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0

) {
    constructor(movieInput: MovieInput) : this(
        movieInput.popularity.toString().toDouble(),
        movieInput.vote_count.toString().toInt(),
        movieInput.video.toString().toBoolean(),
        movieInput.poster_path.toString(),
        movieInput.adult.toString().toBoolean(),
        movieInput.backdrop_path.toString(),
        movieInput.original_language.toString(),
        movieInput.original_title.toString(),
        movieInput.title.toString(),
        movieInput.vote_average.toString().toDouble(),
        movieInput.overview.toString(),
        movieInput.release_date.toString(),
        movieInput.id.toString().toInt()
    )

    companion object {
        const val TABLE_NAME = "movie"
    }

}