package com.lvqingyang.iwuster.Dean

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.bigkoo.pickerview.OptionsPickerView
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.*
import com.lvqingyang.frame.tool.MyToast
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.helper.*
import com.lvqingyang.iwuster.other.NoDataException
import com.lvqingyang.iwuster.other.NoNetworkException
import kotlinx.android.synthetic.main.activity_class_schedule.*
import kotlinx.android.synthetic.main.layout_class_schedule_menu_pop.view.*
import kotlinx.android.synthetic.main.layout_week_pop.view.*

class ClassScheduleActivity : BaseActivity() {

    //课表真正显示的Fragment
    private var mFragment: ClassScheduleFragment? = null
    private var mShowFragment: ClassScheduleFragment? = null
    //选择周次的弹出框
    private lateinit var mPopWeek: PopupWindow
    //右侧menu
    private lateinit var mPopMenu: PopupWindow
    //当前周
    private var mCurrentWeek=1
    //上次显示的周
    private var mLastWeek=1
    //课表数据
    private lateinit var mCourseLiteList: List<CourseLite>
    //修改学期周次选择器的数据，懒加载
    private val mTerms: List<String> by lazy{ resources.getStringArray(R.array.terms).asList()}
    private val mWeeks: List<String> by lazy {
        List(25){ "第${it+1}周" }
    }
    //是否加载过课表
    private var mIsLoadCourse=false
    //右侧menu监听事件
    private val mMenuItemClickListener=object: View.OnClickListener{
        override fun onClick(v: View?) {
            mPopMenu.dismiss()
            when(v?.id){
                //修改周次
                R.id.item_week -> showSelectWeekPicker()
                R.id.item_term -> showSelectTermPicker()
                R.id.item_share_schedule -> shareClassSchedule()
                R.id.item_theme -> startActivityForResult(
                        ClassScheduleStyleActivity.newIntent(this@ClassScheduleActivity),
                        REQUEST_STYLE
                )
            }
        }

    }

    companion object {
        private val REQUEST_STYLE=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //不重复添加Fragment
        if (savedInstanceState==null&&mIsLoadCourse) {
            showCurrentWeekClass()
        }
    }

    override fun getLayoutResID()= R.layout.activity_class_schedule

    override fun initListener() {
        //周次选择Popup
        tv_week.setOnClickListener {
            if (mIsLoadCourse) {
                mPopWeek.show(it, PopupWindowBuilder.Direction.BOTTOM)
            }
        }

        //返回本周
        fab_back.setOnClickListener {
            showCurrentWeekClass()
        }

        //菜单
        iv_menu.setOnClickListener {
            if (mIsLoadCourse) {
                mPopMenu.show(it)
            }
        }

        ev.setOnRetryListener{
            loadCourse()
        }

        iv_back.setOnClickListener { finish() }
    }

    override fun loadData() {
        mIsLoadCourse=myPreference.getBool(str(R.string.is_load_course))
        if (mIsLoadCourse) {
            initCourses()
        }else{
            loadCourse()
        }

        initWeekPop()
        initMenuPop()
    }

    override fun showData() {
    }

    //加载课表
    private fun loadCourse() {
//        toast(R.string.load_course)
        ev.loading()
        //成功
        val succ: ()->Unit={
            ev.success()
            initCourses()
            showCurrentWeekClass()
        }
        //失败
        val error: (Exception)->Unit={
            it.printStackTrace()
            when(it){
                is NoNetworkException -> ev.noConnection()
                is NoDataException -> {
                    ev.empty()
                    MyToast.info(this, R.string.no_data)
                    showSelectTermPicker()
                }
                else -> ev.failed()
            }
        }

        getLatestClassSchedule(
                this,
                myPreference.getString(str(R.string.sp_stu_id)),
                succ,
                error
        )
    }

    //初始化课表数据
    private fun initCourses(isReload: Boolean=false) {
        mCourseLiteList= getShowCourse()
        //更新周次
        mCurrentWeek= getCurrentWeek(this)
        mLastWeek=mCurrentWeek

        if (isReload) {//重新加载课表时正确显示
            mFragment=ClassScheduleFragment.newInstance(mCourseLiteList, mCurrentWeek)
        }else {
            if (mFragment == null) {
                mFragment = findFragment()
                        ?: ClassScheduleFragment.newInstance(mCourseLiteList, mCurrentWeek)
            }
        }
    }

    private fun initWeekPop() {
        mPopWeek=PopupWindowBuilder(
                this,
                DisplayHelper.dpToPx(140),
                DisplayHelper.dpToPx( 200)
        ).setContentView(R.layout.layout_week_pop, {
            contentView ->
            val lvWeek=contentView.lv_week as ListView
            val adapter = ArrayAdapter(
                    this,
                    R.layout.item_week,
                    Array(25){
                        val week=it+1
                        "第${week}周"
                    })
            lvWeek.adapter=adapter
            lvWeek.setOnItemClickListener {
                parent, view, position, id ->
                mPopWeek.dismiss()
                val week=position+1
                if(week!=mLastWeek){//不是上次显示的周
                    if (week==mCurrentWeek) {
                        showCurrentWeekClass()
                    }else {
                        mShowFragment=ClassScheduleFragment.newInstance(
                                mCourseLiteList,
                                week,
                                false
                        )
                        getFragmentTransaction()
                                .replace(R.id.container, mShowFragment)
                                .commit()
                        fab_back.visibility= VISIBLE
                    }
                    tv_week.text="第${week}周"
                    mLastWeek=week
                }
            }
        })
                .setOutsideTouchable(true)
                .setFocusable(true)
                .create()
    }

    private fun initMenuPop() {
        mPopMenu= PopupWindowBuilder(this, DisplayHelper.dpToPx(160))
                .setContentView(R.layout.layout_class_schedule_menu_pop, {
                    contentView ->
                    contentView.item_week.setOnClickListener(mMenuItemClickListener)
                    contentView.item_term.setOnClickListener(mMenuItemClickListener)
                    contentView.item_add_course.setOnClickListener(mMenuItemClickListener)
                    contentView.item_theme.setOnClickListener(mMenuItemClickListener)
                    contentView.item_share_schedule.setOnClickListener(mMenuItemClickListener)
                })
                .setOutsideTouchable(true)
                .setFocusable(true)
                .create()
    }

    private fun showCurrentWeekClass() {
        mIsLoadCourse=true
        mShowFragment=mFragment
        getFragmentTransaction()
                .replace(R.id.container, mFragment, ClassScheduleFragment::class.simpleName)
                .commit()
        fab_back.visibility=GONE
        tv_week.text="第${mCurrentWeek}周"
    }

    private fun showSelectWeekPicker(){
        //条件选择器
        val pvOptions = OptionsPickerView.Builder(this){
            options1, options2, options3, v ->
            if (options1!=(mCurrentWeek-1)){//更改当前周
                mCurrentWeek=options1+1
                myPreference.saveInt(str(R.string.sp_zc), mCurrentWeek)
                mFragment= ClassScheduleFragment.newInstance(mCourseLiteList, mCurrentWeek)
                showCurrentWeekClass()
            }
        }
                .setCancelColor(resources.getColor(R.color.colorSecondary))
                .setSubmitColor(resources.getColor(R.color.colorSecondary))
                .setTitleText(str(R.string.current_zc))
                .setLinkage(false)//设置是否联动，默认true
                .setSelectOptions(mCurrentWeek-1)  //设置默认选中项
                .isDialog(true)
                .build()

        pvOptions.setPicker(mWeeks)
        pvOptions.show()
    }


    private fun showSelectTermPicker(){
        val term=myPreference.getInt(str(R.string.sp_term_loaded))
        //条件选择器
        val pvOptions = OptionsPickerView.Builder(this){
            options1, options2, options3, v ->
            if (options1!=term){
                reloadCourse(options1)
            }
        }
                .setCancelColor(resources.getColor(R.color.colorSecondary))
                .setSubmitColor(resources.getColor(R.color.colorSecondary))
                .setTitleText(str(R.string.current_term))
                .setLinkage(false)//设置是否联动，默认true
                .setSelectOptions(term)  //设置默认选中项
                .isDialog(true)
                .build()

        pvOptions.setPicker(mTerms)
        pvOptions.show()
    }

    private fun reloadCourse(term: Int) {
        MyToast.loading(this, R.string.loading)
        val stuId=myPreference.getString(str(R.string.sp_stu_id))
        getClassSchedule(
                this,
                stuId,
                getTerm(stuId, term),
                onSucc = {
                    MyToast.cancel()
                    MyToast.success(this, R.string.load_succ)
                    initCourses(true)
                    showCurrentWeekClass()
                },
                onError = {
                    MyToast.cancel()
                    it.printStackTrace()
                    when(it){
                        is NoNetworkException -> MyToast.error(this, R.string.check_network)
                        is NoDataException -> MyToast.info(this, R.string.no_data)
                    }
                },
                zc = mCurrentWeek
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode){
            REQUEST_STYLE -> if(resultCode== Activity.RESULT_OK) {
                val alpha=myPreference.getFloat(str(R.string.sp_alpha), 0.5f)
                val corner=myPreference.getFloat(str(R.string.sp_corner), 15f)
                mShowFragment?.changeCourseBg(alpha, corner)
                mFragment?.changeCourseBg(alpha, corner)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun shareClassSchedule() {
        val bitmap=DrawableHelper.createBitmapFromView(ll_root)
        val bitmapPath = MediaStore.Images.Media.insertImage(
                getContentResolver(), bitmap,"课表", null
        )
        val bitmapUri = Uri.parse(bitmapPath)
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
        startActivity(Intent.createChooser(intent, "Share"))
    }
}

