package com.example.sangit

import android.content.ContentProviderClient
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class searchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_search, container, false)

        val searchView = view.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)

        val recyclerView: RecyclerView = view.findViewById(R.id.search_view)
        recyclerView.layoutManager= LinearLayoutManager(view.context)


        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)




        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty()!!){
                    val call = retrofitClient.getData(newText)
                    call.enqueue(object : retrofit2.Callback<songData?> {
                        override fun onResponse(call: Call<songData?>, response: Response<songData?>) {
                            val responseBody= response.body()?.data!!
//               Toast.makeText(activity,"total${responseBody}",Toast.LENGTH_SHORT).show()
                            val Adapter =MyAdapter(this, responseBody)
                            recyclerView.adapter= Adapter
                            Adapter.setItemClickListener(object :MyAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
//                        Toast.makeText(context, "hii", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(context, MusicPlay::class.java)
                                    intent.putExtra("title",responseBody[position].title)
                                    intent.putExtra("cover", responseBody[position].album.cover_big)
                                    intent.putExtra("song", responseBody[position].preview)
                                    intent.putExtra("duration", responseBody[position].duration)
                                    startActivity(intent)
                                }

                            })
                        }

                        override fun onFailure(call: Call<songData?>, t: Throwable) {

                        }
                    })
                }
                return false
            }
        })




        return view
    }


}