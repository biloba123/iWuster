package com.lvqingyang.frame.helper

import com.google.gson.Gson
import org.json.JSONArray

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

val GSON: Gson by lazy { Gson() }

inline fun <reified T> String.jsonToList(): List<T>{
    val jsonArr= JSONArray(this)
    return List(jsonArr.length()){
        GSON.fromJson(jsonArr[it].toString(), T::class.java)
    }
}