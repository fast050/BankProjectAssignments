package com.example.bankprojectassignments.data.remote

import com.example.bankprojectassignments.data.remote.dto.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesAPI {

    @Headers(
        "Accept-Version: v1",
    )
    @GET("search/photos")
    suspend fun getPhotos(
        @Query("query") query: String = "cat",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page:Int = 10,
        @Query("client_id") apiKey:String = API_KEY
    ): ImagesResponse

    companion object {
        const val baseUrl = "https://api.unsplash.com/"
        private const val API_KEY = "LEqrWuWMfXGKtHeck39hy5xTuVEdrT6ADA1wbDjyC30"
    }
}