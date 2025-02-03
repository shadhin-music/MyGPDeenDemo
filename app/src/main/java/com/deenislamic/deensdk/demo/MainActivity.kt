package com.deenislamic.deensdk.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.service.network.response.gphome.GPFloatingData
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

        DeenSDKCore.getFloatingCardData(this)
        Log.d("GPMainActivity","Pin Status: ${DeenSDKCore.isFloatingCardPinned()}")


        startBtn.setOnClickListener {
            gphome.visibility = View.GONE
            startBtn.visibility = View.GONE
            openFragment(ViewpagerFragment())
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Replace 'fragment_container' with your container ID
            .commit()
    }

    override fun gpFloatingCardData(data: GPFloatingData?) {
        Log.d("GPMainActivity", "gpFloatingCardData")
    }
}