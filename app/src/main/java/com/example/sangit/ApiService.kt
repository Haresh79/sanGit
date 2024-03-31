package com.example.sangit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService{
    @Headers("X-RapidAPI-Key:417d375300msh4cb67c2747c7725p119e62jsn8147c7e53e1d",
             "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String):Call<songData>
}
