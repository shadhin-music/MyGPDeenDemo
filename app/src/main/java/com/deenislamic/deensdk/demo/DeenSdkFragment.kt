package com.deenislamic.deensdk.demo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.utils.toast
import com.deenislamic.sdk.views.gphome.GPHome

class DeenSdkFragment : Fragment(), DeenSDKCallback {

    private lateinit var  gphome: GPHome

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DeenSDKCore.setupPermissionRequest(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val getview = inflater.inflate(R.layout.fragment_deen_sdk, container, false)

        gphome = getview.findViewById(R.id.gphome)

        return getview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DeenSDKCore.setGPKEY("Test key")
        gphome.initSDK(
            context = requireContext(),
            language = "en",
            baseApiUrl = "https://api.deenislamic.com/api/",
            baseServiceUrl = "https://services.deenislamic.com/api/",
            baseResourceUrl = "",
            callback = this
        )
    }

    override fun DeenRequireToken(){
        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTczODQzOTIzNiIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzQ2MDI2OTQsImV4cCI6MTczNDY4OTA5NCwiaWF0IjoxNzM0NjAyNjk0fQ.9rLR_wAMObbKcg73cGFvu9E3Tk3Ws5c5BzHQM7lW9rsQsySGMkoiAofrup226eJTe34PF41tdekT9a-aBB9TSg"
        DeenSDKCore.setToken(token)

    }

    override fun onDeenTriggerEvent(event_name: String, param: String) {

    }

}