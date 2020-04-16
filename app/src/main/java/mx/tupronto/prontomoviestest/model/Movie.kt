package mx.tupronto.prontomoviestest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Movie.TABLE_NAME)
class Movie {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    var contactId: Int = 0

    @ColumnInfo(name = "popularity")
    val popularity: Double? = null

    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null

    @ColumnInfo(name = "video")
    val video: Boolean? = null

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null

    @ColumnInfo(name = "id")
    val id: Int? = null

    @ColumnInfo(name = "adult")
    val adult: Boolean? = null

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null

    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null

    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>? = null

    @ColumnInfo(name = "title")
    val title: String? = null

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null

    @ColumnInfo(name = "overview")
    val overview: String? = null

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null

    companion object {
        const val TABLE_NAME = "movie"
    }

}