package com.example.o.aad_practice_project.adapters

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


@SuppressLint("WrongConstant")
internal class ViewPagerAdapter(supportFragmentManager: FragmentManager?) :
    FragmentStatePagerAdapter(
        supportFragmentManager!!,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    private val mList: MutableList<Fragment> = ArrayList()
    private val mTitleList: MutableList<String> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return mList[i]
    }

    override fun getCount(): Int {
        return mList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mList.add(fragment)
        mTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
}