package com.sai.fabula.api

import com.sachin.nasa.utils.APODResponse
import retrofit2.http.GET

/**
 * Service to fetch nasa Api
 */
interface ApiService {


    @GET("?api_key=0w1LsUYrqcmCYuI2s3sVILeZFYcLxQqbQJevpfT6")
    suspend fun getAPOD() : APODResponse

    companion object {
        const val URL = "https://api.nasa.gov/planetary/apod/"
    }




}
