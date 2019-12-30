package com.project.alcoholmembership.service

import com.project.alcoholmembership.model.User
import com.project.alcoholmembership.network.APIUtiles
import com.project.alcoholmembership.network.UserAPIService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class UserService {

    companion object {
        var instance = UserService()
    }
//    private val ourInstance = UserService()
    private var mService: UserAPIService? = APIUtiles.getUserService

    fun getInstance(): UserService {
        return instance
    }

    private fun MyPageService() {}

    fun resisterUser(user: User): Observable<Response<User>> {
        return mService!!.createUserInfo(user).subscribeOn(Schedulers.io())
            .doOnNext { res ->
                if (res.code() === HttpsURLConnection.HTTP_OK) {
                    res.body()
                }
            }
    }
    fun getUserInfo(qrid : String): Observable<Response<User>> {
        return mService!!.getUserInfo(qrid).subscribeOn(Schedulers.io())
            .doOnNext { res ->
                if (res.code() === HttpsURLConnection.HTTP_OK) {
                    res.body()
                }
            }
    }

}