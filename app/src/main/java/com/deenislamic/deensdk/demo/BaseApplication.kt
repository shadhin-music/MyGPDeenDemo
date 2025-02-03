package com.deenislamic.deensdk.demo

import android.app.Application
import com.deenislamic.sdk.DeenSDKCore


class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        DeenSDKCore.initSDK(
            context = this,
            language = "en",
            baseApiUrl = "https://mygp-dev.grameenphone.com/mygpapi/deenapi/",
            baseServiceUrl = "https://mygp-dev.grameenphone.com/mygpapi/deenservice/",
            baseResourceUrl = "https://mygp-dev.grameenphone.com/mygpstatic/deencontent/",
            baseGPHomeUrl = "https://mygp-home.deenislamic.com/"
        )
    }

}