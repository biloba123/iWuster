package com.lvqingyang.iwuster

import android.support.design.widget.BottomNavigationView
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.Dean.ClassScheduleActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_discover.*
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity() {

    override fun getLayoutResID()=R.layout.activity_main

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_discover  -> {
            }
        }

        true
    }

    override fun initListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.itemIconTintList=null

        di_class_schedule.setOnClickListener { startActivity<ClassScheduleActivity>() }
    }

    override fun loadData() {
        //init user
        myPreference.saveString(str(R.string.sp_stu_id), "201513137125")
    }

    override fun showData() {

    }

}
