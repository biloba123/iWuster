package com.lvqingyang.frame.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
abstract class BaseFragment: Fragment() {

    private lateinit var mAppCompatActivity: AppCompatActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //是否保留Fragment
        //setRetainInstance(true);

        //是否有menu，然后重写onCreateOptionsMenu, onOptionsItemSelected
        setHasOptionsMenu(true)
        mAppCompatActivity = activity as AppCompatActivity
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        savedInstanceState?.let {
            getBundleExtras(savedInstanceState)
        }
        val view=inflater.inflate(getLayoutResID(), container, false )
        initListener()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        showData()
    }


    abstract protected fun getLayoutResID(): Int

    abstract protected fun initListener()

    protected abstract fun loadData()

    protected abstract fun showData()

    open protected fun getBundleExtras(bundle: Bundle?){}

    //初始化toolbar
    fun initToolbar(toolbar: Toolbar, title: String, isDisplayHomeAsUp: Boolean) {
            mAppCompatActivity.setSupportActionBar(toolbar)
            val actionBar = mAppCompatActivity.getSupportActionBar()
            if (actionBar != null) {
                actionBar.setTitle(title)
                actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp)
            }
    }
}