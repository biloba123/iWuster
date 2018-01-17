package com.lvqingyang.iwuster

import android.content.Intent
import android.support.design.widget.BottomNavigationView
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.Dean.ClassScheduleActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun getLayoutResID()=R.layout.activity_main

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_discover  -> {
                startActivity(Intent(this, ClassScheduleActivity::class.java))
            }
        }

        true
    }

    override fun initListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.itemIconTintList=null
    }

    override fun loadData() {
        //init user
        myPreference.saveString(str(R.string.sp_stu_id), "201513137125")
    }

    override fun showData() {

    }

}
