package com.lvqingyang.frame.helper

import android.content.Context
import com.lvqingyang.frame.tool.MyPreference
import com.lvqingyang.frame.tool.NetWorkUtils

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/13
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

/**
 * 获取字符串资源
 */
fun Context.str(id: Int)=getString(id)

fun Context.color(id: Int)=resources.getColor(id)

/**
 * 判断网络状态
 */
fun Context.isNetworkConnected()=NetWorkUtils.isNetworkConnected(this)

fun Context.isWifiConnected()=NetWorkUtils.isWifiConnected(this)

fun Context.isMobileConnected()=NetWorkUtils.isMobileConnected(this)

/**
 * 获取SharedPreferences
 */
fun Context.getPreference()=MyPreference.getInstance(this.applicationContext)