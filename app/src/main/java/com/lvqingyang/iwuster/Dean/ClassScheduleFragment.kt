package com.lvqingyang.iwuster.Dean

import android.os.Bundle
import android.os.Parcelable
import android.widget.RelativeLayout
import com.lvqingyang.frame.base.BaseFragment
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.helper.getWeekOfDate
import com.lvqingyang.iwuster.view.CourseTextView
import kotlinx.android.synthetic.main.class_schedule_fragment.*
import org.jetbrains.anko.toast
import java.util.*



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
class ClassScheduleFragment : BaseFragment() {

    private var mWeekItemMarTop: Int=0
    private var mWeekItemMarLeft: Int=0
    private var mWeekItemHeight: Int=0
    private lateinit var mRlCourses: Array<RelativeLayout>
    private lateinit var mCourseLiteList: List<CourseLite>
    private var mWeek=1
    private var mIsCurrentWeek=true
    private var mCorner=15f
    private var mAlpha=0.5f

    //实例化Fragment
    companion object {
        const val ARG_COURSE_LITES="ARG_COURSE_LITES"
        const val ARG_WEEK="ARG_WEEK"
        const val ARG_IS_CURRENT_WEEK="ARG_IS_CURRENT_WEEK"

        fun newInstance(
                clList: List<CourseLite>, //课程数据
                week: Int,                       //显示周次
                isCurrentWeek: Boolean=true//是否为当前周
        ): ClassScheduleFragment {
            val fragment = ClassScheduleFragment()

            val args = Bundle()
            args.putParcelableArrayList(ARG_COURSE_LITES, clList as ArrayList<out Parcelable>)
            args.putInt(ARG_WEEK, week)
            args.putBoolean(ARG_IS_CURRENT_WEEK, isCurrentWeek)

            fragment.arguments = args
            return fragment
        }
    }


    override fun getLayoutResID()= R.layout.class_schedule_fragment

    override fun initListener() {
    }

    override fun loadData() {
        mAlpha=myPreference.getFloat(activity!!.str(R.string.sp_alpha), 0.5f)
        mCorner=myPreference.getFloat(activity!!.str(R.string.sp_corner), 15f)
        mWeekItemMarTop=resources.getDimensionPixelSize(R.dimen.weekItemMarTop)
        mWeekItemMarLeft=resources.getDimensionPixelSize(R.dimen.weekItemMarLeft)
        mWeekItemHeight=resources.getDimensionPixelSize(R.dimen.weekItemHeight)
        mRlCourses = Array(7){
            contentPanel.getChildAt(it+1) as RelativeLayout
        }

        //获取传过来的数据
        mCourseLiteList= arguments?.getParcelableArrayList(ARG_COURSE_LITES)!!
        mWeek= arguments?.getInt(ARG_WEEK)!!
        mIsCurrentWeek= arguments?.getBoolean(ARG_IS_CURRENT_WEEK)!!
    }

    override fun showData() {
        //显示当前是周几
        if (mIsCurrentWeek) {
            ll_week_name.getChildAt(getWeekOfDate()).setBackgroundResource(R.drawable.class_schedule_current_week_bg)
        }

        var lastCl: CourseLite?=null
        mCourseLiteList.forEach {
            //该判断为了保证在同一时间有多节课时，未完结课始终在最上面
            if(!(lastCl!=null&& lastCl!!.time==it.time&&mWeek !in it.startWeek .. it.endWeek)){
                val week=(it.time[0]-'0').toInt()
                val tv=CourseTextView(activity!!, it, mWeekItemMarLeft, mWeekItemMarTop,
                        mAlpha, mCorner,
                        mWeek !in it.startWeek .. it.endWeek)
                //点击回调
                tv.setOnClickListener { v ->  context?.toast(it.name) }

                mRlCourses[week-1].addView(tv)
            }
            lastCl=it
        }
    }

    public fun changeCourseBg(alpha: Float, corner: Float){
        mAlpha=alpha
        mCorner=corner
        mRlCourses.forEach {
            for(i in 0 until  it.childCount){
                (it.getChildAt(i) as CourseTextView).setBgDrawable(mAlpha, mCorner)
            }
        }
    }
}