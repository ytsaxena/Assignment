package com.sai.fabula.database

import com.sai.fabula.di.ApiModule
import com.sachin.nasa.utils.APODResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class APODRepository(private val nasaApiModule: ApiModule) {

    fun getAPODImage() = flow {

        try {
            val apiResponse = fetchImageFromApi()

            val APODapiResponse = apiResponse.body()

            if (apiResponse.isSuccessful) {
               // saveRemoteData(newsApiResponse?.articles!!)
                if (APODapiResponse != null) {
                  //  Log.d("dssdsd",APODapiResponse.hdurl.toString())
                    emit(APODapiResponse)
                }
            } else {
                emit("ERROR")
            }
        } catch (e: Exception) {
          //  Log.d("efewf",e.message.toString())
                emit("Network error!Please Check again ")
        }

    }.flowOn(Dispatchers.IO)


    private suspend fun fetchImageFromApi() : APODResponse = nasaApiModule.getApiService().getAPOD()


}
