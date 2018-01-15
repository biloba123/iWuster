package com.lvqingyang.iwuster

import android.util.Log
import kotlin.coroutines.experimental.buildSequence

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
suspend fun doSomething(n: Int):Int{
    var sum=0;
    for (i in 1..n){
        sum+=i
    }
    return sum
}

val fibonacci= buildSequence {
    if (BuildConfig.DEBUG) Log.d("Test", ": start")
    var a=0
    var b=1
    var count=0

    while (true){
        if (BuildConfig.DEBUG) Log.d("Test", ": step ${++count}")
        val temp=a+b
        yield(temp)
        a=b
        b=temp
    }

    if (BuildConfig.DEBUG) Log.d("Test", ": End")
}