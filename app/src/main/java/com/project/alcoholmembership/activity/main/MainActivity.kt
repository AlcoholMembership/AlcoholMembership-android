package com.project.alcoholmembership.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.alcoholmembership.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { MainPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰페이저 어댑터 연결
        main_pager.adapter = MainActivity@adapter

    }
}
