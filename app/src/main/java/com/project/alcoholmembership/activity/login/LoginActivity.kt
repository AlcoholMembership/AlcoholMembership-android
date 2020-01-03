package com.project.alcoholmembership.activity.login

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.Utility.getPackageInfo
import com.project.alcoholmembership.activity.main.MainActivity
import com.project.alcoholmembership.R
import com.project.alcoholmembership.activity.signup.SignUpActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity() {

    private var callback: SessionCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)

        //토큰 만료시 갱신
        if (Session.getCurrentSession().isOpenable) {
            Session.getCurrentSession().checkAndImplicitOpen()
        }

    }

    //키해시를 받는 메소드
    fun getKeyHash(context: Context?): String? {
        val packageInfo: PackageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null
        for (signature in packageInfo.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                Log.e("Not Found : $signature", e.toString())
            }
        }
        return null
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpened() {
            Log.d("Success Login", "success login")
            requestMe()
        }

        // 세션 실패시
        override fun onSessionOpenFailed(exception: KakaoException) {
            Log.d("Fail to Login", "reason : $exception")
        }

        //유저의 정보를 받아오는 메소드
        private fun requestMe() {
            val keys = ArrayList<String>()
            keys.add("properties.nickname")
            keys.add("properties.profile_image")

            UserManagement.requestMe(object : MeResponseCallback() {

                override fun onFailure(errorResult: ErrorResult?) {
                    Log.e(ContentValues.TAG, "error message = ${errorResult?.errorMessage}")
                }

                override fun onSessionClosed(errorResult: ErrorResult) {
                    redirectLoginActivity()
                }

                override fun onNotSignedUp() {
                }

                override fun onSuccess(response: UserProfile?) {
                    if (response != null) {
                        d("@@User nickname", "" + response.nickname)
                        d("@@User id", "" + response.id)
                        d("@@User Image URL", ""+response.profileImagePath)
                        redirectSignUpActivity(response.profileImagePath, response.nickname, response.id)
                    }
                    else
                        Toast.makeText(applicationContext,"서버에 문제가 생겨 접속에 실패하였습니다.",Toast.LENGTH_SHORT)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    protected fun redirectSignUpActivity(imageURL: String, nickname: String, token: Long) {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.putExtra("imageURL",imageURL).putExtra("token",token.toString()).putExtra("nickname",nickname)
        this@LoginActivity.startActivity(intent)
        this@LoginActivity.finish()
    }

    protected fun redirectLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
