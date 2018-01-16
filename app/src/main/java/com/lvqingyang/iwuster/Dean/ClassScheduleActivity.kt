package com.lvqingyang.iwuster.Dean

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.DisplayHelper
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.helper.getShowCourse
import kotlinx.android.synthetic.main.activity_class_schedule.*
import kotlinx.android.synthetic.main.layout_week_pop.view.*

class ClassScheduleActivity : BaseActivity() {

    private var mFragment: ClassScheduleFragment? = null
    private lateinit var mPopWeek: PopupWindow
    private val mPopWeekYoff: Int by lazy{
        (tv_week.width-DisplayHelper.dpToPx(140))/2
    }
    private var mCurrentWeek=1
    private var mLastWeek=1
    private lateinit var mCourseLiteList: List<CourseLite>

    companion object {
          fun newIntent(context: Context): Intent {
                val starter: Intent = Intent(context, ClassScheduleActivity::class.java)
                return starter
          }
     }

    override fun getLayoutResID()= R.layout.activity_class_schedule

    override fun initListener() {
        //周次选择Popup
        tv_week.setOnClickListener {
            if (mPopWeek.isShowing) {
                mPopWeek.dismiss()
            }else{
                mPopWeek.showAsDropDown(it, mPopWeekYoff, 0)
            }
        }

        //返回本周
        fab_back.setOnClickListener {
            showCurrentWeekClass()
        }
    }

    override fun loadData() {
        mCourseLiteList=getShowCourse()
        if (mFragment==null) {
            mFragment= findFragment()
                    ?:ClassScheduleFragment.newInstance(mCourseLiteList, 14)
        }

        mCurrentWeek=14
        mLastWeek=14

        initWeekPop()
    }

    override fun showData() {
        tv_week.text="第${mCurrentWeek}周"
    }

    private fun initWeekPop() {
        val contentView=layoutInflater.inflate(R.layout.layout_week_pop, null)
        val lvWeek=contentView.lv_week as ListView
        val adapter = ArrayAdapter(
                this,
                R.layout.item_week,
                Array(25){
                    val week=it+1
                    if (week==mCurrentWeek) {
                        "第${week}周（本周）"
                    }else {
                        "第${week}周"
                    }
                })
        lvWeek.adapter=adapter
        lvWeek.setOnItemClickListener {
            parent, view, position, id ->
            mPopWeek.dismiss()
            val week=position+1
            if(week!=mLastWeek){
                if (week==mCurrentWeek) {
                    showCurrentWeekClass()
                }else {
                    getFragmentTransaction()
                            .replace(R.id.container,
                                    ClassScheduleFragment.newInstance(
                                            mCourseLiteList,
                                            week,
                                            false)
                            )
                            .commit()
                    fab_back.visibility= VISIBLE
                }
                tv_week.text="第${week}周"
                mLastWeek=week
            }

        }

        mPopWeek=PopupWindow(this)
        mPopWeek.width= DisplayHelper.dpToPx(140)
        mPopWeek.height=DisplayHelper.dpToPx( 200)
        mPopWeek.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPopWeek.isOutsideTouchable=true
        mPopWeek.isFocusable=true
        mPopWeek.contentView=contentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState==null) {
            showCurrentWeekClass()
        }
    }

    private fun showCurrentWeekClass() {
        getFragmentTransaction()
                .replace(R.id.container, mFragment, ClassScheduleFragment::class.simpleName)
                .commit()
        fab_back.visibility=GONE
        tv_week.text="第${mCurrentWeek}周"
    }


}

