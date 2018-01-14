package com.lvqingyang.iwuster

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.lvqingyang.frame.helper.jsonToList
import com.lvqingyang.frame.helper.str
import com.lvqingyang.frame.tool.MyPreference
import com.lvqingyang.iwuster.bean.Course
import com.lvqingyang.iwuster.bean.CourseLite
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null
    private lateinit var myPreference: MyPreference

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_discover  -> Thread{

//                for (i in 100 .. 200){
                    val courseList= getYxkc("201313137125", "2015-2016-2")
                            .jsonToList<Course>()
                    for (c in courseList){
                        if (BuildConfig.DEBUG) Log.d("MainActivity",
                                "${c.kcmc} | ${c.jsmc} | ${c.kcsj} | ${c.kkzc}\n${c.kkzcmx}");
                        val courseLites=CourseLite.parseCourse(c)
                        for (cl in courseLites){
                            if (BuildConfig.DEBUG) Log.d("MainActivity", "${cl.time} | ${cl.room} | ${cl.startWeek}-${cl.endWeek}");
                        }
                    }
//                }

            }.start()
        }

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myPreference = MyPreference.getInstance(this)
        //init user
        myPreference.saveString(str(R.string.stu_id), "201513137125")


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.itemIconTintList=null


    }
}
