package com.hetic.musicontheway.network

import com.hetic.musicontheway.network.data.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("v1/coverage/fr-idf/places")
    fun getDatas(@Query("q") query: String) : Call<Result>
}