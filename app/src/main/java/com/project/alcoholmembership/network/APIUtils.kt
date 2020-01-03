package com.project.alcoholmembership.network

import com.project.alcoholmembership.BuildConfig

object APIUtiles {

    private const val BASE_URL = BuildConfig.APIServerBaseURL

    val getUserService: UserAPIService
        get() = RetrofitClient.getClient(BASE_URL).create(UserAPIService::class.java)
}

