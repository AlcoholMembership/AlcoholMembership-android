package com.project.alcoholmembership.activity.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.project.alcoholmembership.fragment.QRReaderFragment
import com.project.alcoholmembership.fragment.QRcodeFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0       ->  QRcodeFragment()
            1       ->  QRReaderFragment()
            else    ->  null
        }
    }

    override fun getCount() = 2;
}