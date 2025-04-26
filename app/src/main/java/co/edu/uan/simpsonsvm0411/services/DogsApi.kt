package co.edu.uan.simpsonsvm0411.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// API: https://api.thedogapi.com/v1/images/search?limit=10
interface DogsApi {
    @GET("images/search")
    suspend fun search(@Query("limit") n: Int = 1) : List<DogImage>

    companion object {
        var api : DogsApi? = null

        fun getInstance() : DogsApi {
            if (api == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.thedogapi.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(DogsApi::class.java)
            }
            return api!!
        }

    }

}