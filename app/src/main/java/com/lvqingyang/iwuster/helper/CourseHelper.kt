package com.lvqingyang.iwuster.helper

import android.content.Context
import com.lvqingyang.frame.helper.isNetworkConnected
import com.lvqingyang.frame.helper.jsonToList
import com.lvqingyang.iwuster.bean.Course
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.bean.Term
import com.lvqingyang.iwuster.getXnxq
import com.lvqingyang.iwuster.getYxkc
import com.lvqingyang.iwuster.other.NoDataException
import com.lvqingyang.iwuster.other.NoNetworkException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.litepal.crud.DataSupport
import java.util.*

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

fun getShowCourse()= DataSupport.where("time != ?", "").order("time").find(CourseLite::class.java)

fun getClassSchedule(
        context: Context,
        stuId: String,
        term: String,
        onSucc: (()->Unit)?=null,
        onError: ((e: Exception)->Unit)?=null) {

    if (context.isNetworkConnected()) {
        context.doAsync {
            getClassScheduleAndSave(context, stuId, term, onSucc, onError)
        }
    }else {
        onError?.invoke(NoNetworkException())
    }
}

fun getLatestClassSchedule(
        context: Context,
        stuId: String,
        onSucc: (()->Unit)?=null,
        onError: ((e: Exception)->Unit)?=null) {
    if (context.isNetworkConnected()) {
        try {
            context.doAsync {
                val terms = getXnxq().jsonToList<Term>()
                getClassScheduleAndSave(context, stuId, terms[terms.lastIndex].xnxq01id, onSucc, onError)
            }
        } catch (e: Exception) {
            onError?.invoke(e)
        }
    }else {
        onError?.invoke(NoNetworkException())
    }
}

fun getClassScheduleAndSave(context: Context, stuId: String, term: String, onSucc: (() -> Unit)?, onError: ((e: Exception) -> Unit)?) {
        try {
            val response = getYxkc(stuId, term)
            if (response == "没有数据") {
                throw NoDataException()
            } else {
                val courseList = response.jsonToList<Course>()
                val courseLiteList = parseCourseList(courseList)

                DataSupport.deleteAll(Course::class.java)
                DataSupport.deleteAll(CourseLite::class.java)

                DataSupport.saveAll(courseList)
                DataSupport.saveAll(courseLiteList)

                context.runOnUiThread { onSucc?.invoke() }
            }
        } catch (e: Exception) {
            context.runOnUiThread { onError?.invoke(e) }
        }
}



fun parseCourseList(courses: List<Course>): List<CourseLite> {
    val courseLites=ArrayList<CourseLite>()
    courses.forEach { courseLites.addAll(parseCourse(it)) }
    return courseLites
}

fun parseCourse(course: Course): List<CourseLite> {
    val courseLites = ArrayList<CourseLite>()

    val _name = course.kcmc
    val _week = course.kkzc
    val _time = course.kcsj
    val _room = course.jsmc
    val _teacher = course.jsxm

    if (_week != null && _time != null) {
        val weeks = _week.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val times = _time.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var rooms: Array<String>? = null

        if (_room != null) {
            rooms = _room.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        val multi = weeks.size / times.size
        for (i in weeks.indices) {
            val courseLite = CourseLite(course)
            courseLite.name = _name
            courseLite.teacher = _teacher

            if (_room != null) {
                courseLite.room = rooms!![i / multi]
            }

            courseLite.time = times[i / multi]

            val w = weeks[i]
            val sw: String
            val ew: String
            if (w.contains("-")) {
                val ws = w.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                sw = ws[0]
                ew = ws[1]
            } else {
                ew = w
                sw = ew
            }
            courseLite.startWeek = Integer.valueOf(sw)!!
            courseLite.endWeek = Integer.valueOf(ew)!!

            courseLites.add(courseLite)
        }
    } else {
        val courseLite = CourseLite(course)
        courseLite.name = _name
        courseLite.teacher = _teacher
        courseLites.add(courseLite)
    }

    course.courseLites=courseLites
    return courseLites
}