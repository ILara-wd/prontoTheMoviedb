package mx.tupronto.prontomoviestest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.tupronto.prontomoviestest.service.data.MovieInput

@Entity(tableName = Movie.TABLE_NAME)
class Movie() {

    companion object {
        const val TABLE_NAME = "Movie"
    }

    @ColumnInfo(name = "popularity")
    var popularity: Double? = 0.0

    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0

    @ColumnInfo(name = "video")
    var video: Boolean? = false

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "adult")
    var adult: Boolean? = false

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = 0.0

    @ColumnInfo(name = "overview")
    var overview: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0

    constructor(movieInput: MovieInput?) : this() {
        this.popularity = movieInput?.popularity.toString().toDouble()
        this.voteCount = movieInput?.vote_count.toString().toInt()
        this.video = movieInput?.video.toString().toBoolean()
        this.posterPath = movieInput?.poster_path.toString()
        this.adult = movieInput?.adult.toString().toBoolean()
        this.backdropPath = movieInput?.backdrop_path.toString()
        this.originalLanguage = movieInput?.original_language.toString()
        this.originalTitle = movieInput?.original_title.toString()
        this.title = movieInput?.title.toString()
        this.voteAverage = movieInput?.vote_average.toString().toDouble()
        this.overview = movieInput?.overview.toString()
        this.releaseDate = movieInput?.release_date.toString()
        this.id = movieInput?.id.toString().toInt()
    }

}