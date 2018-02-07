package com.lvqingyang.frame.tool

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/12
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

class MyPreference private constructor(context: Context) {
    private val mSharedPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor
    private val mGson: Gson

    //是否登录
    val isLogined: Boolean
        get() = getBool(KEY_IS_LOGIN)


    init {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        mEditor = mSharedPreferences.edit()
        mGson = Gson()
    }

    //save and get string
    fun saveString(tag: String, value: String) = mEditor.putString(tag, value).apply()

    @JvmOverloads
    fun getString(tag: String, def: String? = null) = mSharedPreferences.getString(tag, def)

    //save and get int
    fun saveInt(tag: String, value: Int) = mEditor.putInt(tag, value).apply()


    fun getInt(tag: String, def: Int= DEFAULT_INT) = mSharedPreferences.getInt(tag, def)

    //save and get float
    fun saveFloat(tag: String, value: Float) = mEditor.putFloat(tag, value).apply()


    fun getFloat(tag: String, def: Float= 0f) = mSharedPreferences.getFloat(tag, def)

    //save and get long
    fun saveLong(tag: String, value: Long) = mEditor.putLong(tag, value).apply()


    fun getLong(tag: String, def: Long= DEFAULT_LONG) = mSharedPreferences.getLong(tag, def)

    //save and get bool
    fun saveBool(tag: String, value: Boolean) = mEditor.putBoolean(tag, value).apply()

    @JvmOverloads
    fun getBool(tag: String, def: Boolean = false): Boolean = mSharedPreferences.getBoolean(tag, def)

    //save and get object
    fun saveObject(tag: String, obj: Any) = mEditor.putString(tag, mGson.toJson(obj)).apply()

    fun <T> getObject(tag: String, classOfT: Class<T>) = mGson.fromJson(getString(tag), classOfT)

    fun clearData(key: String) = mEditor.remove(key).apply()

    //登录成功后保存用户信息
    fun saveUser(user: Any) {
        saveBool(KEY_IS_LOGIN, true)
        saveObject(KEY_USER, user)
    }

    //获取用户信息
    fun <T> getUser(classOfT: Class<T>): T? {
        var user: T? = null
        if (isLogined) {
            user = getObject(KEY_USER, classOfT)
        }
        return user
    }

    //退出登录
    fun logOut() {
        saveBool(KEY_IS_LOGIN, false)
        mEditor.remove(KEY_USER)
                .apply()
    }

    companion object {
        @Volatile private  var sMPreference: MyPreference?=null

        //default int value
        val DEFAULT_INT = -1
        val DEFAULT_LONG = 0L
        //default float value
        val DEFAULT_FLOAT = 0f

        private val KEY_IS_LOGIN = "IS_LOGIN"
        private val KEY_USER = "USER"

        fun getInstance(context: Context): MyPreference {
            if (sMPreference == null) {
                synchronized(MyPreference::class) {
                    if (sMPreference == null) {
                        sMPreference = MyPreference(context)
                    }
                }
            }
            return sMPreference as MyPreference
        }
    }
}