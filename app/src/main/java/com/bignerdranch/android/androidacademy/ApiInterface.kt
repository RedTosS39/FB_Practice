import com.bignerdranch.android.androidacademy.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie")
    fun getMovies(@Query("token") token : String,
                  @Query("search") search: String,
                  @Query("field") field: String) : Call<Movies>

    companion object {

        var BASE_URL = " https://api.kinopoisk.dev/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}