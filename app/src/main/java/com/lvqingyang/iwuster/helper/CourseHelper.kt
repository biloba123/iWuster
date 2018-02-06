package com.lvqingyang.iwuster.helper

import android.content.Context
import android.util.Log
import com.lvqingyang.frame.helper.getPreference
import com.lvqingyang.frame.helper.isNetworkConnected
import com.lvqingyang.frame.helper.jsonToList
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.BuildConfig
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.Course
import com.lvqingyang.iwuster.bean.CourseLite
import com.lvqingyang.iwuster.bean.Term
import com.lvqingyang.iwuster.net.getXnxq
import com.lvqingyang.iwuster.net.getYxkc
import com.lvqingyang.iwuster.other.NoDataException
import com.lvqingyang.iwuster.other.NoNetworkException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.litepal.crud.DataSupport
import java.util.*

/**
 * 课表相关工具
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/15
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

//获取课表显示数据
fun getShowCourse()= DataSupport.where("time != ?", "").order("time").find(CourseLite::class.java)

//获取指定学期课表
fun getClassSchedule(
        context: Context,
        stuId: String,
        term: String,
        onSucc: (()->Unit)?=null,
        onError: ((e: Exception)->Unit)?=null,
        zc: Int=1
) {

    if (context.isNetworkConnected()) {
        context.doAsync {
            getClassScheduleAndSave(context, stuId, term, onSucc, onError, zc)
        }
    }else {
        onError?.invoke(NoNetworkException())
    }
}

//获取最新学期课表
fun getLatestClassSchedule(
        context: Context,
        stuId: String,
        onSucc: (()->Unit)?=null,
        onError: ((e: Exception)->Unit)?=null
) {
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

//获取课表并保存
fun getClassScheduleAndSave(
        context: Context,
        stuId: String,
        term: String,
        onSucc: (() -> Unit)?,
        onError: ((e: Exception) -> Unit)?,
        zc: Int=1
) {
        try {
            val response = getYxkc(stuId, term)
            if (BuildConfig.DEBUG) Log.d("CourseHelper", "getClassScheduleAndSave: $term \n"+response)
            if (response == "没有数据") {
                throw NoDataException()
            } else {
                val courseList = response.jsonToList<Course>()
                val courseLiteList = parseCourseList(courseList)

                DataSupport.deleteAll(Course::class.java)
                DataSupport.deleteAll(CourseLite::class.java)

                DataSupport.saveAll(courseList)
                DataSupport.saveAll(courseLiteList)

                val myPreference=context.getPreference()
                myPreference.saveBool(context.str(R.string.is_load_course), true)
                myPreference.saveLong(context.str(R.string.sp_date), getTime())
                myPreference.saveInt(context.str(R.string.sp_week), getWeekOfDate())
                myPreference.saveInt(context.str(R.string.sp_zc), zc)
                myPreference.saveInt(context.str(R.string.sp_term_loaded), getWhichTerm(stuId, term))

                context.runOnUiThread {
                    onSucc?.invoke()
                }
            }
        } catch (e: Exception) {
            context.runOnUiThread { onError?.invoke(e) }
        }
}

//获取是哪个学期
fun getWhichTerm(stuId: String, term: String)
     =(term.substring(0, 4).toInt()-stuId.substring(0, 4).toInt())*2+(term[term.lastIndex]-'1').toInt()

fun getTerm(stuId: String, term: Int): String{
    val joinYear=stuId.substring(0, 4).toInt()
    val year=joinYear+term/2
    return "${year}-${year+1}-${1+term%2}"
}

//更新当前周
fun getCurrentWeek(context: Context): Int{
    val myPreference=context.getPreference()
    val lastTime=myPreference.getLong(context.str(R.string.sp_date))
    val diff= getDiffDays(lastTime)
    var currentWeek = myPreference.getInt(context.str(R.string.sp_zc))
    

    if(diff!=0) {
        val week = myPreference.getInt(context.str(R.string.sp_week))

        when {
            diff > 0 -> currentWeek += (diff + week - 1) / 7
            diff < 0 -> currentWeek += (diff + week - 7) / 7
        }

        myPreference.saveLong(context.str(R.string.sp_date), getTime())
        myPreference.saveInt(context.str(R.string.sp_week), getWeekOfDate())
        myPreference.saveInt(context.str(R.string.sp_zc), currentWeek)
    }

    return currentWeek
}


private fun parseCourseList(courses: List<Course>): List<CourseLite> {
    val courseLites=ArrayList<CourseLite>()
    courses.forEach { courseLites.addAll(parseCourse(it)) }
    return courseLites
}

private fun parseCourse(course: Course): List<CourseLite> {
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