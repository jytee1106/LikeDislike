package com.example.likedislike

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    //Module-level
    var like: Int = 0
    var dislike: Int = 0

    //Define a module level Preference
    lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","onCreate")

        //Initialise the preferences
        preferences = getPreferences(Context.MODE_PRIVATE)



        imageViewUp.setOnClickListener {
            like ++
            textViewUp.text = like.toString()
        }

        imageViewDown.setOnClickListener {
            dislike ++
            textViewDown.text = dislike.toString()
        }

        buttonCall.setOnClickListener {
            val phone = "tel:0174538509"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(phone))
            //validate the intent is workable
            if (intent.resolveActivity(packageManager)!= null) {
                startActivity(intent)
            }else{
                //inform user to install an app that can handle
                //this intent
            }
        }
    }

    override fun onStart() {
        Log.d("MainActivity","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity","onResume")
        //Retrive the existing like and dislike values
        like = preferences.getInt(getString(R.string.like),0)
        dislike = preferences.getInt(getString(R.string.dislike),0)

        textViewUp.text = like.toString()
        textViewDown.text = dislike.toString()
        super.onResume()
    }

    override fun onStop() {
        Log.d("MainActivity","onStop")
        super.onStop()
    }

    override fun onPause() {
        Log.d("MainActivity","onPause")
        with(preferences.edit()){
            putInt(getString(R.string.like),like)
            putInt(getString(R.string.dislike),dislike)
            commit()
        }
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("MainActivity","onDestroy")
        super.onDestroy()
    }
}
