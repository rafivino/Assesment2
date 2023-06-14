package org.d3if3142.assesment2_6706213142.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "rafivino/Assesment2/ubinAPI/static_API.json"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UbinApiService {
    @GET("static_API.json")
    suspend fun getInfo(): String
}

object UbinApi {
 val service: UbinApiService by lazy {
    retrofit.create(UbinApiService::class.java)
 }
}