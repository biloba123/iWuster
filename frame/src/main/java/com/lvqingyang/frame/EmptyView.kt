package com.lvqingyang.frame

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.lvqingyang.frame.helper.initImage
import com.lvqingyang.frame.helper.initText
import kotlinx.android.synthetic.main.ev_empty.view.*
import kotlinx.android.synthetic.main.ev_fail.view.*
import kotlinx.android.synthetic.main.ev_layout.view.*
import kotlinx.android.synthetic.main.ev_loading.view.*
import kotlinx.android.synthetic.main.ev_no_connection.view.*
import java.util.*

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2017/12/29
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

public open class EmptyView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var mViewShow: View?=null
    public var STATE_LOAD: Byte=1
    public var STATE_FAIL: Byte=2
    public var STATE_EMPTY: Byte=3
    public var STATE_NO_CONNECT: Byte=4
    public var STATE_SUCCESS: Byte=5


    init {
        val inflater=LayoutInflater.from(context)
        inflater.inflate(R.layout.ev_layout, this)

        if (context!=null && attrs != null) {
             val arr=context.theme.obtainStyledAttributes(
                    attrs, R.styleable.EmptyView, 0, 0)
            try {
                //show view
                val layoutId=arr.getResourceId(R.styleable.EmptyView_view_show, -1)
                if (layoutId!=-1) {
                    mViewShow=inflater.inflate(layoutId, null)
                    container.addView(mViewShow, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                }

                arr.initImage(iv_empty, R.styleable.EmptyView_empty_icon)
                arr.initImage(iv_fail, R.styleable.EmptyView_fail_icon)
                arr.initImage(iv_no_connection, R.styleable.EmptyView_no_connection_icon)

                arr.initText(tv_empty, R.styleable.EmptyView_empty_text)
                arr.initText(tv_empty_info, R.styleable.EmptyView_empty_text_info)
                arr.initText(tv_fail, R.styleable.EmptyView_fail_text)
                arr.initText(tv_fail_info, R.styleable.EmptyView_fail_text_info)

                setIndicator()
            }finally {
                arr.recycle()
            }
        }
    }

    public fun loading()=changeState(layout_loading)

    public fun failed()=changeState(layout_failed)

    public fun noConnection()=changeState(layout_no_connection)

    public fun empty()=changeState(layout_empty)

    public fun success()=changeState(mViewShow)

    public open fun changeState(state: Byte){
        when(state){
            STATE_EMPTY -> empty()
            STATE_FAIL -> failed()
            STATE_LOAD -> loading()
            STATE_NO_CONNECT ->noConnection()
            STATE_SUCCESS -> success()
        }
    }



    public fun setOnRetryListener(listener: OnClickListener){
        layout_no_connection.setOnClickListener{
            loading()
            listener.onClick(it)
        }
    }

    private fun changeState(viewShow: View?){
        for (i in 0 until container.childCount){
            container.getChildAt(i).visibility= View.GONE
        }
        viewShow?.visibility= View.VISIBLE
    }

    private fun setIndicator() {
        val arrayOfString = resources.getStringArray(R.array.arr_indicator)
        val i = Random().nextInt(arrayOfString.size)
        av_loading.setIndicator(arrayOfString[i])
    }

}