package com.lvqingyang.iwuster.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ShapeDrawable
import com.lvqingyang.frame.helper.ColorHelper

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

class CourseBgDrawable(color: Int, alpha: Float, corner: Float): ShapeDrawable() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mColor: Int
    private var mCorner: Float

    init {
        mPaint.style= Paint.Style.FILL
        mColor=ColorHelper.setColorAlpha(color, alpha)
        mCorner=corner
    }


    override fun draw(canvas: Canvas?) {
        mPaint.color=mColor
        val rectF=RectF(bounds)
        canvas?.drawRoundRect( rectF, mCorner, mCorner, mPaint)
    }

}