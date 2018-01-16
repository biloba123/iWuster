package com.lvqingyang.iwuster.view

import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.drawable.CourseBgDrawable

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/16
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
class CourseTextView(
        context: Context,
        course: CourseLite,
        marLeft: Int,
        marTop: Int,
        isComplete: Boolean=false
): TextView(context) {

    private val mCourseColors: IntArray by lazy {
        intArrayOf(
                R.color.course_color_01,
                R.color.course_color_02,
                R.color.course_color_03,
                R.color.course_color_04,
                R.color.course_color_05,
                R.color.course_color_06,
                R.color.course_color_07,
                R.color.course_color_08,
                R.color.course_color_09,
                R.color.course_color_10,
                R.color.course_color_11,
                R.color.course_color_12
        )
    }

    init {
        if (isComplete){
            setTextColor(resources.getColor(R.color.color_25_pure_black))
            background= CourseBgDrawable(
                    resources.getColor(R.color.course_complete)
            )
        }else{
            setTextColor(resources.getColor(R.color.state_color_white))
            background= CourseBgDrawable(
                    resources.getColor(mCourseColors[course.course_id.toInt()%mCourseColors.size])
            )
        }

        isClickable=true
        isFocusable=true
        val padding=resources.getDimensionPixelSize(R.dimen.s_small_spacing)
        setPadding(padding, padding, padding, padding)
        textSize=12f
        text="${course.name}@${course.room}"


        val time=course.time
        val start=time.substring(1, 3).toInt()
        val end=time.substring(time.length-2).toInt()
        val step=end-start
        val weekItemHeight=resources.getDimensionPixelSize(R.dimen.weekItemHeight)
        val lp= RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                step*2*weekItemHeight-2*marTop
        )
        lp.setMargins(
                marLeft,
                weekItemHeight*(start-1)+marTop,
                marLeft,
                0
        )
        layoutParams=lp
    }

}