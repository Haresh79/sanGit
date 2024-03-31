package com.example.sangit

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nav =findViewById<BottomNavigationView>(R.id.bottomNavigation)

        replaceWithFragment(homeFragment())
        nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> replaceWithFragment(homeFragment())
                R.id.item2 -> replaceWithFragment(searchFragment())
                R.id.item3 -> replaceWithFragment(collectionFragment())
                else ->{

                }
            }
            true
        }

        if (isNetworkConnected(this)==false) {
            startActivity(Intent(this, offline::class.java))
        }

    }

    fun replaceWithFragment(fragment: Fragment) {
        val fragManager = supportFragmentManager
        val fragTransition = fragManager.beginTransaction()
        fragTransition.replace(R.id.frameLayout, fragment)
        fragTransition.commit()
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkCapabilities != null && (networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

// Example usage:


}