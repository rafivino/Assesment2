package org.d3if3142.assesment2_6706213142.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3142.assesment2_6706213142.data.Ubin
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "rafivino/Assesment2/ubinAPI/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UbinApiService {
    @GET("static_API.json")
    suspend fun getUbin(): List<Ubin>
}

object UbinApi {
    val service: UbinApiService by lazy {
        retrofit.create(UbinApiService::class.java)
    }

    fun getUbinUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }