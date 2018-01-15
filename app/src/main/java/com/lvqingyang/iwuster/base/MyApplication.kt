package com.lvqingyang.iwuster.base

import android.app.Application
import org.litepal.LitePal

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/15
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //Litepal初始化
        LitePal.initialize(this)
        LitePal.getDatabase();
    }
}