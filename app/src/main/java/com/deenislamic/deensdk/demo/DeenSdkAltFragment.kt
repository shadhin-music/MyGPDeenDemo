package com.deenislamic.deensdk.demo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.utils.toast
import com.deenislamic.sdk.views.gphome.GPHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeenSdkAltFragment : Fragment(), DeenSDKCallback {

    private lateinit var  gphome: GPHome
    override fun onAttach(context: Context) {
        super.onAttach(context)
        DeenSDKCore.setupPermissionRequest(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val getview = inflater.inflate(R.layout.fragment_deen_sdk_alt, container, false)

        gphome = getview.findViewById(R.id.gphome)

        val enBtn: AppCompatButton = getview.findViewById(R.id.enBtn)
        val bnBtn: AppCompatButton = getview.findViewById(R.id.bnBtn)

        enBtn.setOnClickListener {
            //if(DeenSDKCore.GetDeenToken().isNotEmpty() && this@DeenSDKActivity::gphome.isInitialized)
            gphome.changeLanguage("en")
        }

        bnBtn.setOnClickListener {
            //if(DeenSDKCore.GetDeenToken().isNotEmpty() && this@DeenSDKActivity::gphome.isInitialized)
            gphome.changeLanguage("bn")
        }

        return getview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        DeenSDKCore.setGPKEY("MyGP Testing Key")

        gphome.initView("floatingcard",this)


    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if(menuVisible) {
            /*DeenSDKCore.setupCallback(this)*/


        }
    }


    override fun DeenRequireToken(){

        Log.d("GPFloatingCard","DeenRequireToken")

        lifecycleScope.launch(Dispatchers.IO) {
            //delay(5000)
            val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTY4MDM5NjY0NyIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzYxNjczNDMsImV4cCI6MTczNjI1Mzc0MywiaWF0IjoxNzM2MTY3MzQzfQ.s5cnj-d4SoiYyaL1g3VJGy1TG6-Nb-obsvW_gScOfgm9RoqMKuKK0X8JlP408KP5i2VscMtwwC8HFjoRU55XhA"
            DeenSDKCore.setToken(token)
        }
    }

    override fun onDeenTriggerEvent(event_name: String, param: String) {
        Log.d("GPFloatingCard","$event_name $param")
        lifecycleScope.launch(Dispatchers.Main) {
            context?.toast("Event: $event_name,$param")
        }

    }

    override suspend fun DeenTokenExpired():String {

        Log.d("GPFloatingCard","DeenTokenExpired")

        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTY4MDM5NjY0NyIsInJvbGUiOiJTREsiLCJuYmYiOjE3Mzc1NDg5MTYsImV4cCI6MTczNzYzNTMxNiwiaWF0IjoxNzM3NTQ4OTE2fQ.e09fQMdUqRVHyDzYz8_Du1IMIeoCcgxI-C3B43PDmWdZnP03vFTKE4N-lo2TN2jiLdRD0d0-YOfcSMrd63U2_A"

        lifecycleScope.launch(Dispatchers.Main) {
            context?.toast("Token expired")

        }

        return withContext(Dispatchers.IO){
            delay(500)
            token
        }
    }

    override fun onDeenSDKInitSuccess() {
        Log.d("GPFloatingCard","onDeenSDKInitSuccess")
    }

    override fun onDeenSDKInitFailed() {
        Log.d("GPFloatingCard","onDeenSDKInitFailed")
    }


    override fun gpFloatingPinnedStatus(isPinned: Boolean) {
        Log.d("GPFloatingCard","gpFloatingPinnedStatus $isPinned")
    }


}