package com.project.alcoholmembership.activity.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.alcoholmembership.R
import com.project.alcoholmembership.activity.main.MainActivity
import com.project.alcoholmembership.util.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val imageURL = intent.getStringExtra("imageURL")
        val nickname = intent.getStringExtra("nickname")
        val token = intent.getStringExtra("token")

        Log.d("@@@image",""+imageURL)
        Log.d("@@@nickname",""+nickname)
        Log.d("@@@token",""+token)

        kakao_nickname.text = "닉네임 : $nickname"
        kakao_token.text = "token : $token"

        Picasso.get().load(imageURL)
            .transform(CircleTransform.getInstance()).into(kakao_profile)

        btn_main.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
