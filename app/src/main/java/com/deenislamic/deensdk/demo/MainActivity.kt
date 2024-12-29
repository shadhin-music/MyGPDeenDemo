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
        gphome.initSDK(
            context = this,
            language = "en",
            baseApiUrl = "https://api.deenislamic.com/api/",
            baseServiceUrl = "https://services.deenislamic.com/api/",
            baseResourceUrl = "",
            callback = this
        )

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
        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTczODQzOTIzNiIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzQ2MDI2OTQsImV4cCI6MTczNDY4OTA5NCwiaWF0IjoxNzM0NjAyNjk0fQ.9rLR_wAMObbKcg73cGFvu9E3Tk3Ws5c5BzHQM7lW9rsQsySGMkoiAofrup226eJTe34PF41tdekT9a-aBB9TSg"
        DeenSDKCore.setToken(token)

    }

    override fun onDeenTriggerEvent(event_name: String, param: String) {

    }

    override fun DeenTokenExpired() {
        // get callback when api return 401
    }
}