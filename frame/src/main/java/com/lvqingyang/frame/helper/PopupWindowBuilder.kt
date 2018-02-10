package com.lvqingyang.frame.helper

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/19
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
class PopupWindowBuilder(
        context: Context,
        width: Int=ViewGroup.LayoutParams.WRAP_CONTENT,
        height: Int=ViewGroup.LayoutParams.WRAP_CONTENT) {

    enum class Direction{
        TOP, BOTTOM, LEFT, RIGHT
    }

    private val mPopupWindow:PopupWindow
    private val mContext: Context

    init {
        mContext=context
        mPopupWindow= PopupWindow(context)
        mPopupWindow.width=width
        mPopupWindow.height=height
        mPopupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setContentView(layoutId: Int, initView:(contentView: View)->Unit): PopupWindowBuilder{
        val contentView=LayoutInflater.from(mContext).inflate(layoutId, null)
        initView(contentView)
        mPopupWindow.contentView=contentView
        return this
    }

    fun setContentView(contentView: View): PopupWindowBuilder{
        mPopupWindow.contentView=contentView
        return this
    }

    fun setBackground(drawable: Drawable): PopupWindowBuilder{
        mPopupWindow.setBackgroundDrawable(drawable)
        return this
    }

    fun setBackground(resId: Int): PopupWindowBuilder=
            setBackground(mContext.resources.getDrawable(resId))

    fun setOutsideTouchable(isOutsideTouchable: Boolean): PopupWindowBuilder{
        mPopupWindow.isOutsideTouchable=isOutsideTouchable
        return this
    }

    fun setFocusable(isFocusable: Boolean): PopupWindowBuilder{
        mPopupWindow.isFocusable=isFocusable
        return this
    }

    fun create()=mPopupWindow

    fun show(view: View, direction: PopupWindowBuilder.Direction?=null)
            =create().show(view, direction)

}

fun PopupWindow.show(view: View, direction: PopupWindowBuilder.Direction?=null){
    if (direction!=null){
        when(direction){
            PopupWindowBuilder.Direction.TOP ->
                this.showAsDropDown(view, getHorizontalCenterX(view), (view.height+height)*-1)
            PopupWindowBuilder.Direction.BOTTOM ->
                this.showAsDropDown(view, getHorizontalCenterX(view), 0)
            PopupWindowBuilder.Direction.LEFT ->
                this.showAsDropDown(view, this.width*-1, getVerticalCenterY(view))
            PopupWindowBuilder.Direction.RIGHT ->
                this.showAsDropDown(view, view.width, getVerticalCenterY(view))
        }
    }else{
        this.showAsDropDown(view)
    }
}

fun PopupWindow.getHorizontalCenterX(view: View)=(view.width-this.width)/2
fun PopupWindow.getVerticalCenterY(view: View)=(view.height+this.height)/-2

