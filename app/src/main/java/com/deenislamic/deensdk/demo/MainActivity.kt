package com.deenislamic.deensdk.demo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.views.gphome.GPHome

class MainActivity : AppCompatActivity(), DeenSDKCallback {

    private lateinit var startBtn:Button
    private lateinit var  gphome: GPHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        gphome = findViewById(R.id.gphome)

        startBtn = findViewById(R.id.startBtn)

        DeenSDKCore.setGPKEY("Test key")


        startBtn.setOnClickListener {
            gphome.visibility = View.GONE
            startBtn.visibility = View.GONE
            openFragment(DeenSdkFragment())
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Replace 'fragment_container' with your container ID
            .commit()
    }

    override fun DeenRequireToken(){
        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTczODQzOTIzNiIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzYwNjEzNDUsImV4cCI6MTczNjE0Nzc0NSwiaWF0IjoxNzM2MDYxMzQ1fQ.PQFoYneGKVqym0pzW2LX1dTmy9aco-6S6ak-qGSyCauzBWuckUOJqwxjUX8Ad3b9W9PKLD_w3YdvxYBIvnBbbw"
        DeenSDKCore.setToken(token)

    }

    override fun onDeenTriggerEvent(event_name: String, param: String) {

    }

    override suspend fun DeenTokenExpired():String {
        // get callback when api return 401
        return "";
    }
}