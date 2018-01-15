package com.lvqingyang.iwuster

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.lvqingyang.frame.helper.str
import com.lvqingyang.frame.tool.MyPreference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null
    private lateinit var myPreference: MyPreference

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_discover  -> {
//                getLatestClassSchedule(
//                        this,
//                        myPreference.getString(str(R.string.stu_id)),
//                        onSucc = {
//                            toast("Succ")
//                            DataSupport.findAll(CourseLite::class.java).forEach {
//                                if (BuildConfig.DEBUG) Log.d("MainActivity", ": $it")
//                            }
//                        }
//                ){
//                    toast("Error")
//                   it.printStackTrace()
//                }
                startActivity(Intent(this, ClassScheduleActivity::class.java))
            }
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
