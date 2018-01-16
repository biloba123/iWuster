package com.lvqingyang.iwuster.Dean

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.bigkoo.pickerview.OptionsPickerView
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.DisplayHelper
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.helper.getShowCourse
import kotlinx.android.synthetic.main.activity_class_schedule.*
import kotlinx.android.synthetic.main.layout_class_schedule_menu_pop.view.*
import kotlinx.android.synthetic.main.layout_week_pop.view.*
import org.jetbrains.anko.toast

class ClassScheduleActivity : BaseActivity() {

    private var mFragment: ClassScheduleFragment? = null
    private lateinit var mPopWeek: PopupWindow
    private val mPopWeekYoff: Int by lazy{
        (tv_week.width-DisplayHelper.dpToPx(140))/2
    }
    private lateinit var mPopMenu: PopupWindow
    private var mCurrentWeek=1
    private var mLastWeek=1
    private lateinit var mCourseLiteList: List<CourseLite>
    private val mTerms: List<String> by lazy{ resources.getStringArray(R.array.terms).asList()}
    private val mWeeks: List<List<String>> by lazy {
        List(8){ List(25){ "第${it+1}周" } }
    }
    private val mMenuItemClickListener=object: View.OnClickListener{
        override fun onClick(v: View?) {
            mPopMenu.dismiss()
            when(v?.id){
                R.id.item_term_week -> showSelectTermAndWeekPicker()
            }
        }

    }

    companion object {
          fun newIntent(context: Context): Intent {
                val starter: Intent = Intent(context, ClassScheduleActivity::class.java)
                return starter
          }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState==null) {
            showCurrentWeekClass()
        }
    }

    override fun getLayoutResID()= R.layout.activity_class_schedule

    override fun initListener() {
        //周次选择Popup
        tv_week.setOnClickListener {
            mPopWeek.showAsDropDown(it, mPopWeekYoff, 0)
        }

        //返回本周
        fab_back.setOnClickListener {
            showCurrentWeekClass()
        }

        //菜单
        iv_menu.setOnClickListener {
            mPopMenu.showAsDropDown(it)
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
        initMenuPop()
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

    private fun initMenuPop() {
        val contentView=layoutInflater.inflate(R.layout.layout_class_schedule_menu_pop, null)
        mPopMenu=PopupWindow(this)
        mPopMenu.width= DisplayHelper.dpToPx(160)
        mPopMenu.height=ViewGroup.LayoutParams.WRAP_CONTENT
        mPopMenu.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPopMenu.isOutsideTouchable=true
        mPopMenu.isFocusable=true
        mPopMenu.contentView=contentView

        contentView.item_term_week.setOnClickListener(mMenuItemClickListener)
        contentView.item_add_course.setOnClickListener(mMenuItemClickListener)
        contentView.item_theme.setOnClickListener(mMenuItemClickListener)
    }


    private fun showCurrentWeekClass() {
        getFragmentTransaction()
                .replace(R.id.container, mFragment, ClassScheduleFragment::class.simpleName)
                .commit()
        fab_back.visibility=GONE
        tv_week.text="第${mCurrentWeek}周"
    }

    private fun showSelectTermAndWeekPicker(){
        //条件选择器
        val pvOptions = OptionsPickerView.Builder(this){
            options1, options2, options3, v -> toast("$options1   $options2")
        }
                .setCancelColor(resources.getColor(R.color.app_color_theme_7))
                .setSubmitColor(resources.getColor(R.color.app_color_theme_7))
                .setLinkage(false)//设置是否联动，默认true
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .build()

        pvOptions.setPicker(mTerms, mWeeks)
        pvOptions.show()
    }


}

