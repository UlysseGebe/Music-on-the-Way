package com.hetic.musicontheway.network

import com.hetic.musicontheway.network.data.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {

    @Headers("Authorization: c655c7f8-756a-48a1-b63b-04df7cabfcfd")
    @GET("places")
    fun getdatas(@Query("q") query: String) : Call<Result>
}