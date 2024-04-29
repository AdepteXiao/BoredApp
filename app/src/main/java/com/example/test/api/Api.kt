package com.example.test.api

import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


data class ActivityResponse(
    val activity: String?,
    val type: String?,
    val participants: Int,
    val price: Double,
    val link: String?,
    val key: String?,
    val accessibility: Double
)


class Api {
    companion object {

        private val apiService = createRetrofit().create(ApiService::class.java)

        private val userPartToApiPart = mapOf(
            "any" to Pair(null, null),
            "1" to Pair(1, 1),
            "2" to Pair(2, 2),
            "3 - 4" to Pair(3, 4),
            "5 - 6" to Pair(5, 6)
        )

        private val userPriceToApiPrice = mapOf(
            "any" to Pair(null, null),
            "free" to Pair(0.0, 0.0),
            "cheap" to Pair(0.1, 0.3),
            "normal" to Pair(0.4, 0.7),
            "expensive" to Pair(0.8, 1.0)
        )

        fun apiPriceToUserPrice(price: Double): String {
            return when {
                price <= 0.0 -> "free"
                price in 0.1..0.3 -> "cheap"
                price in 0.4..0.7 -> "normal"
                price >= 0.8 -> "expensive"
                else -> "any"
            }
        }



        private fun convert(type: String, parts: String, price: String): Map<String, Any?> {
            val apiType = if (type == "any") null else type
            val apiParts = userPartToApiPart[parts]
            val apiPrice = userPriceToApiPrice[price]
            val minPart = apiParts?.first
            val maxPart = apiParts?.second
            val minPrice = apiPrice?.first
            val maxPrice = apiPrice?.second
            val res =  mapOf(
                "type" to apiType,
                "minparticipants" to minPart,
                "maxparticipants" to maxPart,
                "minprice" to minPrice,
                "maxprice" to maxPrice
            ).filterValues { it != null }
            Log.d("API", res.toString())
            return res
        }

        suspend fun call(type: String, parts: String, price: String): ActivityResponse? {
            val params = convert(type, parts, price)
            return withContext(Dispatchers.IO) {
                try {
                    val response = apiService.getRandomActivity(
                        params["type"] as String?,
                        params["minparticipants"] as Int?,
                        params["maxparticipants"] as Int?,
                        params["minprice"] as Double?,
                        params["maxprice"] as Double?,
                    )
                    if (response != null) {
                        Log.d("API", "RESPONSE $response")
                        response
                    } else {
                        Log.d("API", "RESPONSE $response")
                        null
                    }
                } catch (e: Exception) {
                    Log.d("API", "RESPONSE $e")
                    null
                }
            }
        }


    }
}


interface ApiService {
    @GET("activity")
    suspend fun getRandomActivity(
        @Query("type") type: String?,
        @Query("minparticipants") minparticipants: Int?,
        @Query("maxparticipants") maxparticipants: Int?,
        @Query("minprice") minprice: Double?,
        @Query("maxprice") maxprice: Double?
    ): ActivityResponse
}

fun createRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()


    return Retrofit.Builder()
        .baseUrl("http://www.boredapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}