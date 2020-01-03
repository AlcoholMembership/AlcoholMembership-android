package com.project.alcoholmembership.activity.login

import android.app.Activity
import android.app.Application
import com.kakao.auth.KakaoSDK

class GlobalApplication : Application() {

    companion object {

        private var obj: GlobalApplication? = null
        private var currentActivity: Activity? = null

        fun getGlobalApplicationContext(): GlobalApplication?{
            return obj
        }

        fun getCurrentActivity(): Activity ?{
            return currentActivity
        }

//        // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
//        fun setCurrentActivity(currentActivity: Activity) {
//            Companion.currentActivity = currentActivity
//        }

    }

    override fun onCreate() {
        super.onCreate()
        obj = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

}