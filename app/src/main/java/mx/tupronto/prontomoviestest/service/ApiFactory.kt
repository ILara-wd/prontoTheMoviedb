package mx.tupronto.prontomoviestest.service

object ApiFactory {

    val movieApi: MovieApi = RetrofitFactory
        .retrofit("https://api.themoviedb.org/3/")
        .create(MovieApi::class.java)

}