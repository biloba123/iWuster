package com.lvqingyang.iwuster

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.Dean.ClassScheduleActivity
import com.lvqingyang.iwuster.Dean.ScoreActivity
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity() {

    override fun getLayoutResID()=R.layout.main_activity

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
        di_score.setOnClickListener { startActivity<ScoreActivity>() }
    }

    override fun loadData() {
        //init user
        myPreference.saveString(str(R.string.sp_stu_id), "201501124043")
    }

    override fun showData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (BuildConfig.DEBUG) Log.d("MainActivity", "onDestroy: ")
    }

}
