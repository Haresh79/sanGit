package com.example.sangit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class playlist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_playlist)
        val myRecyclerView: RecyclerView= findViewById(R.id.playlistrv)
        myRecyclerView.layoutManager= LinearLayoutManager(this)

        val RetrofitClient = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val singer = intent.getStringExtra("artist")!!

        val call = RetrofitClient.getData(singer)
        call.enqueue(object : Callback<songData>{
            override fun onResponse(call: Call<songData>, response: Response<songData>) {
                var responseBody= response.body()?.data!!
                myRecyclerView.adapter= playlistAdapter(responseBody)

            }

            override fun onFailure(call: Call<songData>, t: Throwable) {

            }

        })

    }
}