package com.lvqingyang.iwuster.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.lvqingyang.iwuster.R
import kotlinx.android.synthetic.main.discover_item_view.view.*

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
class DiscoverItem(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.discover_item_view, this)
        if (context!=null && attrs != null) {
            val arr=context.theme.obtainStyledAttributes(
                    attrs, R.styleable.DiscoverItem, 0, 0)
            try {
                iv_ic.setImageResource(arr.getResourceId(R.styleable.DiscoverItem_icon, R.mipmap.ic_launcher))
                tv_name.text=arr.getString(R.styleable.DiscoverItem_name)
            }finally {
                arr.recycle()
            }
        }

    }

}