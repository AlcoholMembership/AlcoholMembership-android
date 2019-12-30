package com.project.alcoholmembership.network

import android.text.TextUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val httpClient = OkHttpClient.Builder()

        fun getClient(url: String): Retrofit {
            var retrofit =  Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()

            return retrofit
        }

//        fun getClientWithToken(url: String, accessToken: String): Retrofit? {
//            var retrofit: Retrofit? = null
//            val builder = Retrofit.Builder().baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
//
//
//            if (!TextUtils.isEmpty(accessToken)) {
//                val interceptor = AuthenticationInterceptor(accessToken)
//
//                if (!httpClient.interceptors().contains(interceptor)) {
//                    httpClient.addInterceptor(interceptor)
//
//                    builder.client(httpClient.build())
//                    retrofit = builder.build()
//                }
//            }
//
//            return retrofit
//        }
    }
}