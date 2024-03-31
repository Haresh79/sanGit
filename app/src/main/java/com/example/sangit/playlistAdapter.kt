package com.example.sangit

import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class playlistAdapter(var data: List<Data>):RecyclerView.Adapter<playlistAdapter.viewHolder>(){

    class viewHolder(var view: View):RecyclerView.ViewHolder(view){
        val poster = view.findViewById<ImageView>(R.id.cover)
        val title = view.findViewById<TextView>(R.id.songname)
        val ppbtn = view.findViewById<ImageButton>(R.id.PPBtn)
        val background = view.findViewById<LinearLayout>(R.id.LinearLayout)
        var play:Boolean=true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.eaach_playlist_item, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentData = data[position]
        Picasso.get().load(currentData.album.cover).into(holder.poster)
        holder.title.text= currentData.title
        val mediaPlayer = MediaPlayer.create(holder.itemView.context, currentData.preview.toUri())
        holder.ppbtn.setOnClickListener {
          if (holder.play==true) {
              mediaPlayer.start()
              holder.background.setBackgroundColor(Color.parseColor("#7B0F3A"))
              holder.ppbtn.setImageResource(R.drawable.baseline_pause_circle_24)
              holder.play=false
          }else{
              mediaPlayer.pause()
              holder.background.setBackgroundColor(Color.parseColor("#8A1343"))
              holder.ppbtn.setImageResource(R.drawable.baseline_play_arrow_24)
              holder.play=true
          }
        }

    }

}