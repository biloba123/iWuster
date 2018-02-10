package com.lvqingyang.frame.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.lvqingyang.frame.R
import com.lvqingyang.frame.helper.getPreference

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
abstract class BaseActivity: AppCompatActivity() {
    protected val mFragmentManager=supportFragmentManager
    protected val myPreference by lazy { getPreference() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            getBundleExtras(savedInstanceState)
        }

        setContentView(getLayoutResID())

        initListener()
        loadData()
        showData()
    }


    protected abstract fun getLayoutResID(): Int

    protected abstract fun initListener()

    protected abstract fun loadData()

    protected abstract fun showData()

    open protected fun getBundleExtras(savedInstanceState: Bundle){}

    protected fun initToolbar(title: String, isDisplayHome: Boolean) {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        val localActionBar = supportActionBar
        if (localActionBar != null) {
            localActionBar.setTitle(title)
            localActionBar.setDisplayHomeAsUpEnabled(isDisplayHome)
        }
    }


    protected fun initActionbar(titleId: Int, isDisplayHome: Boolean) {
        initActionbar(getString(titleId), isDisplayHome)
    }

    protected fun initActionbar(title: String, isDisplayHome: Boolean) {
        val localActionBar = supportActionBar
        if (localActionBar != null) {
            localActionBar.title = title
            localActionBar.setDisplayHomeAsUpEnabled(isDisplayHome)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    protected inline fun <reified T> findFragment(): T?=
            mFragmentManager.findFragmentByTag(T::class.simpleName)?.let {
                it as T
            }

    protected fun getFragmentTransaction()=mFragmentManager.beginTransaction()

}