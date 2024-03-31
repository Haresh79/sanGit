package com.example.sangit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView

class collectionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        val a1=view.findViewById<ImageView>(R.id.a1)
        val a2=view.findViewById<ImageView>(R.id.a2)
        val a3=view.findViewById<ImageView>(R.id.a3)
        val a4=view.findViewById<ImageView>(R.id.a4)
        val a5=view.findViewById<ImageView>(R.id.a5)

        a1.setImageResource(R.drawable.arijit)
        a2.setImageResource(R.drawable.sheryaghoshle)
        a3.setImageResource(R.drawable.sonunigam)
        a4.setImageResource(R.drawable.nehakakker)
        a5.setImageResource(R.drawable.armaan)

        val singers = listOf("Arijit Singh", "Shreya Ghoshal", "Sonu Nigam", "Neha Kakkar", "Armaan Malik")

        a1.setOnClickListener { singer(singers, 0)}
        a2.setOnClickListener {singer(singers, 1)}
        a3.setOnClickListener {singer(singers, 2)}
        a4.setOnClickListener {singer(singers, 3)}
        a5.setOnClickListener {singer(singers, 4)}

        val language = listOf("Hindi songs", "English songs", "Panjabi songs", "Telugu songs", "Tamil songs")

        view.findViewById<CardView>(R.id.c1).setOnClickListener { singer(language, 0)}
        view.findViewById<CardView>(R.id.c2).setOnClickListener {singer(language, 1)}
        view.findViewById<CardView>(R.id.c3).setOnClickListener {singer(language, 2)}
        view.findViewById<CardView>(R.id.c4).setOnClickListener {singer(language, 3)}
        view.findViewById<CardView>(R.id.c5).setOnClickListener {singer(language, 4)}


        return view
    }
    private fun artistname(name: String){
        val intent = Intent(context, playlist::class.java)
        intent.putExtra("artist", name)
        startActivity(intent)
    }
    private  fun singer(singers:List<String>, i : Int){
        artistname(singers[i])
    }

}