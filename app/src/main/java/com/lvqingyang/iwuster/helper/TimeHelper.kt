package com.lvqingyang.iwuster.helper

import java.text.SimpleDateFormat
import java.util.*


/**
 * 日期工具
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

//获取下一节课
fun getNextCourse()
        = when (Integer.valueOf(SimpleDateFormat("HH").format(Date())).toInt()) {
                in 0..9 -> 1
                in 10..11 ->3
                in 12..15 ->5
                in 16..17 ->7
                in 18..20 ->9
                else ->11
            }

fun getTime() = System.currentTimeMillis()

fun getDiffDays(last: Long): Int{
    var nt= getTime()

    var large = nt
    var small = last
    val isLater=nt>last
    if(!isLater){
        large=last
        small=nt
    }

    val diff=large-small
    val days=(diff / 86400000L).toInt()

    large-=days*86400000L
    val format=SimpleDateFormat("dd")
    val same=if(format.format(Date(large))==format.format(small)) 0 else 1

    return (days+same)*(if(isLater) 1 else -1)
}

fun getWeekOfDate(): Int {
    val arrayOfInt = intArrayOf(7, 1, 2, 3, 4, 5, 6)
    val localCalendar = Calendar.getInstance()
    localCalendar.time = Date()
    var i = -1 + localCalendar.get(Calendar.DAY_OF_WEEK)
    if (i < 0) i = 0
    return arrayOfInt[i]
}
