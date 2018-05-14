package com.lvqingyang.iwuster

import android.content.DialogInterface
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.EditText
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.jsonToList
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.Dean.ClassScheduleActivity
import com.lvqingyang.iwuster.Dean.ScoreActivity
import com.lvqingyang.iwuster.bean.Score
import com.lvqingyang.iwuster.net.getXscjcx
import com.lvqingyang.iwuster.net.getYxkc
import kotlinx.android.synthetic.main.discover_fragment.*
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : BaseActivity() {

    override fun getLayoutResID() = R.layout.main_activity

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_mine -> {
                val et = layoutInflater.inflate(R.layout.account_edit_dialog, null) as EditText
                AlertDialog.Builder(this)
                        .setTitle("修改账号")
                        .setView(et)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, { dialogInterface: DialogInterface, i: Int ->
                            val stuId = et.text
                            if (stuId.length == 12) {
                                myPreference.saveString(str(R.string.sp_stu_id), stuId.toString())
                                myPreference.saveBool(str(R.string.is_load_course), false)
                            } else {
                                toast("学号格式错误")
                            }
                        }).show()
            }
        }

        true
    }

    override fun initListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.itemIconTintList = null

        di_class_schedule.setOnClickListener { startActivity<ClassScheduleActivity>() }
        di_score.setOnClickListener { startActivity<ScoreActivity>() }
    }

    override fun loadData() {
        //init user
        myPreference.saveString(str(R.string.sp_stu_id), "201513137125")
    }

    override fun showData() {
        doAsync {
            var stuId: String
            var cs: String
            for (i in 7000..7200) {
                stuId = "20151313" + i
                cs = getYxkc(stuId, "2017-2018-2")
                if (cs.contains("教学班5032")) {
                    val scores = getXscjcx(stuId)
                    val list = scores.jsonToList<Score>()
                    if (BuildConfig.DEBUG) Log.d("MainActivity", "showData: " + list[0].xm)
                    if (BuildConfig.DEBUG) Log.d("MainActivity", "showData: " + stuId)
                }
            }
        }
    }

}
