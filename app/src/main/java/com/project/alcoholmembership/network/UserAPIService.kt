package com.project.alcoholmembership.network

import com.project.alcoholmembership.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPIService {

    @POST("userInfo/")
    fun createUserInfo(@Body user: User): Observable<Response<User>>

    @GET("userInfo/{qrid}")
    fun getUserInfo(@Path("qrid") qrid : String) : Observable<Response<User>>
}