package mx.tupronto.prontomoviestest.service.data

import mx.tupronto.prontomoviestest.data.Movie

class MovieGenreInput {
    val page: Int? = null
    val total_results: Int? = null
    val total_pages: Int? = null
    val results: List<MovieInput>? = null
}

class MovieInput {
    var popularity: Double? = null
    var vote_count: Int? = null
    var video: Boolean? = null
    var poster_path: String? = null
    var id: Int? = null
    var adult: Boolean? = null
    var backdrop_path: String? = null
    var original_language: String? = null
    var original_title: String? = null
    var title: String? = null
    var vote_average: Double? = null
    var overview: String? = null
    var release_date: String? = null
    var isFavorite: Boolean = false

    constructor(movie: Movie?) {
        this.popularity = movie?.popularity
        this.vote_count = movie?.voteCount
        this.video = movie?.video
        this.poster_path = movie?.posterPath
        this.id = movie?.id
        this.adult = movie?.adult
        this.backdrop_path = movie?.backdropPath
        this.original_language = movie?.originalLanguage
        this.original_title = movie?.originalTitle
        this.title = movie?.title
        this.vote_average = movie?.voteAverage
        this.overview = movie?.overview
        this.release_date = movie?.releaseDate
        this.isFavorite = false
    }

}