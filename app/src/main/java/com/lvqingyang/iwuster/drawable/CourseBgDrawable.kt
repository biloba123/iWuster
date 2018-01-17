package com.lvqingyang.iwuster.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable

/**
 * 课表显示的背景
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/15
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

class CourseBgDrawable(color: Int): ShapeDrawable() {
    private var mPaint: Paint

    init {
        mPaint=Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color=color
        mPaint.style= Paint.Style.FILL
    }


    override fun draw(canvas: Canvas?) {
        val rectF=RectF(bounds)
        canvas?.drawRoundRect( rectF, 15f, 15f, mPaint)
    }

}