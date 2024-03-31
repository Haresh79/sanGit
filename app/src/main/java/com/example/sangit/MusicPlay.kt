package com.example.sangit

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import java.util.logging.Handler

class MusicPlay : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_music_play)

        var songState: Boolean = true
        var totalTime = 0

        val sTitle=findViewById<TextView>(R.id.stitle)
        val sCover= findViewById<ImageView>(R.id.scover)
        val ppbtn = findViewById<ImageButton>(R.id.ppbtn)
        val seekBar= findViewById<SeekBar>(R.id.seekbar)

        val songTitle = intent.getStringExtra("title")
        val songCover= intent.getStringExtra("cover")
        val songurl= intent.getStringExtra("song")
//        val duration= intent.getIntExtra("duration", 0)

        sTitle.text = songTitle
        Picasso.get().load(songCover).into(sCover)
        var mediaPlayer= MediaPlayer.create(this, songurl?.toUri())
        mediaPlayer.setVolume(1f, 1f)
        mediaPlayer.start()
        totalTime=mediaPlayer.duration
        ppbtn.setOnClickListener {
            if (songState) {
                mediaPlayer.pause()
                ppbtn.setImageResource(R.drawable.baseline_play_arrow_24)
                songState= false
            }
            else{
                mediaPlayer.start()
                ppbtn.setImageResource(R.drawable.baseline_pause_circle_24)
                songState= true
            }
        }
        // when user use seekbar
        seekBar.max= totalTime
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }

        })

        val handle = android.os.Handler()
        handle.postDelayed(object :Runnable{
            override fun run() {
                seekBar.progress=mediaPlayer.currentPosition
                handle.postDelayed(this, 1000)
            }

        }, 0)

    }
}