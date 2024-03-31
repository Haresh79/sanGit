package com.example.sangit

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Callback

class MyAdapter(var activity: Callback<songData?>, var dataset: List<Data>) : RecyclerView.Adapter<MyAdapter.viewHolder>() {

    lateinit var myListener:onItemClickListener
    interface onItemClickListener{
        fun  onItemClick(position: Int)
    }
    fun setItemClickListener(Listener:onItemClickListener){
        myListener= Listener
    }

    inner  class viewHolder(var view : View, listener:onItemClickListener): RecyclerView.ViewHolder(view){
        val poster= view.findViewById<ImageView>(R.id.poster)
        val title= view.findViewById<TextView>(R.id.title)
        val singer = view.findViewById<TextView>(R.id.artist)
            init {
                view.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return viewHolder(view, myListener)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentData = dataset[position]

        holder.title.text= currentData.title
        holder.singer.text= currentData.artist.name
        Picasso.get().load(currentData.album.cover).into(holder.poster)


    }
}
