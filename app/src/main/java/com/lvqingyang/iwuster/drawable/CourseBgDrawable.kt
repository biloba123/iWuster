package com.lvqingyang.iwuster.drawable

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable

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

class CourseBgDrawable(color: Int): ShapeDrawable() {
    private var mPaint: Paint
    private var mColor: Int
    private val mShadow=0f

    init {
        mColor=color

        mPaint=Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style= Paint.Style.STROKE
        mPaint.color=Color.parseColor("#DEE0E2")
        mPaint.strokeWidth=mShadow
    }


    override fun draw(canvas: Canvas?) {
        var rectF=RectF(bounds)
        canvas?.drawRoundRect(rectF, 15f, 15f, mPaint)

        mPaint.color=mColor
        mPaint.style= Paint.Style.FILL
        rectF=RectF(bounds.left+mShadow, bounds.top+mShadow, bounds.right-mShadow, bounds.bottom-mShadow)
        canvas?.drawRoundRect( rectF, 15f, 15f, mPaint)
    }

}