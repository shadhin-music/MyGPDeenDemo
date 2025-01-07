package com.deenislamic.deensdk.demo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.deenislamic.sdk.DeenSDKCallback
import com.deenislamic.sdk.DeenSDKCore
import com.deenislamic.sdk.utils.toast
import com.deenislamic.sdk.views.gphome.GPHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeenSdkFragment : Fragment(), DeenSDKCallback {

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
        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTczODQzOTIzNiIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzYwNjEzNDUsImV4cCI6MTczNjE0Nzc0NSwiaWF0IjoxNzM2MDYxMzQ1fQ.PQFoYneGKVqym0pzW2LX1dTmy9aco-6S6ak-qGSyCauzBWuckUOJqwxjUX8Ad3b9W9PKLD_w3YdvxYBIvnBbbw"
        DeenSDKCore.setToken(token)

    }

    override fun onDeenTriggerEvent(event_name: String, param: String) {

    }

    override suspend fun DeenTokenExpired():String {

        val token  = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJBcHBsaWNhdGlvbiI6IkRlZW4gSXNsYW0iLCJuYW1lIjoiODgwMTczODQzOTIzNiIsInJvbGUiOiJTREsiLCJuYmYiOjE3MzU0NjY3MzksImV4cCI6MTczNTU1MzEzOSwiaWF0IjoxNzM1NDY2NzM5fQ.hTo8P3p8KL3kQIiYxqUlM2E7MSSwbktK9wyp5BiZGz-kSTcCv8S1LZOvZudkeOBym5gwE_pHJ6K_172e3gdX4A"

        lifecycleScope.launch(Dispatchers.Main) {
            context?.toast("Token expired")

        }

        return withContext(Dispatchers.IO){
            delay(500)
            token
        }
    }

}