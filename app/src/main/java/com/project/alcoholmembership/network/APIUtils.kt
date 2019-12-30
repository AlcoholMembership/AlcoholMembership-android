package com.project.alcoholmembership.network

import com.project.alcoholmembership.BuildConfig

object APIUtiles {

    private const val BASE_URL = BuildConfig.APIServerBaseURL

    val getUserService: UserAPIService
        get() = RetrofitClient.getClient(BASE_URL).create(UserAPIService::class.java)

//    fun getDrinkService(accessToken: String): DrinkAPIService {
//        return RetrofitClient.getClientWithToken(BASE_URL, accessToken)!!.create(DrinkAPIService::class.java)
//    }
//
//    fun getHistoryService(accessToken: String): HistoryAPIService {
//        return RetrofitClient.getClientWithToken(BASE_URL, accessToken)!!.create(HistoryAPIService::class.java)
////    }
}

