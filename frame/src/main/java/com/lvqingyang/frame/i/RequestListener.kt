package com.lvqingyang.frame.i

/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/13
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
interface RequestListener<T>{
    public fun onSucc(response: String)
    fun onError(e: Throwable)
}