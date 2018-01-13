package com.lvqingyang.frame.helper

import android.content.res.TypedArray
import android.widget.ImageView
import android.widget.TextView

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/7
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

/**
 * 扩展TypedArray的方法
 */
public fun TypedArray.initImage(iv: ImageView, index: Int){
    val drawable=this.getDrawable(index)
    if (drawable != null) {
        iv.setImageDrawable(drawable)
    }
}

public fun TypedArray.initText(tv: TextView, index: Int){
    val text=this.getString(index)
    if (text != null) {
        tv.text=text
    }
}