package com.example.test.api

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.http.GET
import retrofit2.http.Query


data class ActivityResponse(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: String,
    val accessibility: Double
)


val userPartToApiPart = mapOf(
    "any" to Pair(null, null),
    "1" to Pair(1, 1),
    "2" to Pair(2, 2),
    "3 - 4" to Pair(3, 4),
    "5 - 6" to Pair(5, 6)
)

val userPriceToApiPrice = mapOf(
    "any" to Pair(null, null),
    "free" to Pair(0.0, 0.0),
    "cheap" to Pair(0.1, 0.3),
    "normal" to Pair(0.4, 0.7),
    "expensive" to Pair(0.8, 1.0)
)

suspend fun callAPi(type: String, parts: String, price: String): ActivityResponse {
    val apiType = if (type == "any") null else type
    val apiParts = userPartToApiPart[parts]
    val minPart = apiParts?.first
    val maxPart = apiParts?.second
    val apiPrice = userPriceToApiPrice[price]
    val minPrice = apiPrice?.first
    val maxPrice = apiPrice?.second


    val apiService = createRetrofit().create(ApiService::class.java)
    return apiService.getRandomActivity(apiType, minPart, maxPart, minPrice, maxPrice).await()
}


interface ApiService {
    @GET("getRandomActivity")
    suspend fun getRandomActivity(
        @Query("type") type: String?,
        @Query("minparticipants") minparticipants: Int?,
        @Query("maxparticipants") maxparticipants: Int?,
        @Query("minprice") minprice: Double?,
        @Query("maxprice") maxprice: Double?
    ): Call<ActivityResponse>
}

fun createRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()


    return Retrofit.Builder()
        .baseUrl("http://www.boredapi.com/api/activity?")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}