package com.lvqingyang.iwuster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import android.widget.TextView
import com.lvqingyang.iwuster.drawable.CourseBgDrawable
import com.lvqingyang.iwuster.helper.getShowCourse
import kotlinx.android.synthetic.main.fragment_class_schedule.*

class ClassScheduleActivity : AppCompatActivity() {
    private val mCourseColors= intArrayOf(
            R.color.app_color_theme_1,
            R.color.app_color_theme_2,
            R.color.app_color_theme_3,
            R.color.app_color_theme_4,
            R.color.app_color_theme_5,
            R.color.app_color_theme_6,
            R.color.app_color_theme_7,
            R.color.app_color_theme_8,
            R.color.app_color_theme_9
    )
    private var mWeekItemMarTop: Int=0
    private var mWeekItemMarLeft: Int=0
    private var mCourseItemHeight: Int=0
    private var mWeekItemHeight: Int=0
    private lateinit var mLlCourses: Array<RelativeLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_schedule)

        mWeekItemMarTop=resources.getDimensionPixelSize(R.dimen.weekItemMarTop)
        mWeekItemMarLeft=resources.getDimensionPixelSize(R.dimen.weekItemMarLeft)
        mCourseItemHeight=resources.getDimensionPixelSize(R.dimen.courseItemHeight)
        mWeekItemHeight=resources.getDimensionPixelSize(R.dimen.weekItemHeight)
        mLlCourses= Array(7){
            contentPanel.getChildAt(it+1) as RelativeLayout
        }

        getShowCourse().forEach {
            val tv=layoutInflater.inflate(R.layout.tv_course, null) as TextView
            tv.text="${it.name}@${it.room}"
            tv.background=CourseBgDrawable(resources.getColor(mCourseColors[it.course_id.toInt()%mCourseColors.size]))

            val week=(it.time[0]-'0').toInt()
            val start=it.time.substring(1, 3).toInt()
            val end=it.time.substring(it.time.length-2).toInt()
            val step=end-start
            val lp=RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    step*2*mCourseItemHeight+(step-1)*2*mWeekItemMarTop
            )
            lp.setMargins(
                    mWeekItemMarLeft,
                    mWeekItemHeight*(start-1)+mWeekItemMarTop,
                    mWeekItemMarLeft,
                    0
            )
            tv.layoutParams=lp

            mLlCourses[week-1].addView(tv)
        }
    }


}
