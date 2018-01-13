package com.lvqingyang.frame.tool

import okhttp3.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.concurrent.TimeUnit


/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　　　　  ┃
 * 　┃　　　━　　    ┃ ++ + + +
 * ████━████     ┃+
 * 　┃　　　　　　  ┃ +
 * 　┃　　　┻　　  ┃
 * 　┃　　　　　　  ┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃　　　　　　　　　　　
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug！　
 * 　　　┃　　　┃　　+　　　　　　　　　
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/3/15
 * Email：biloba12345@gamil.com
 * Info：OkHttp网络请求
 */


class MyOkHttp private constructor() {
    private val mClient: OkHttpClient

    init {
        mClient = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 同步发送GET请求
     * @param url
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    operator fun get(url: String): String {
        val request = Request.Builder()
                .url(url)
                .build()

        val response = mClient.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body()!!.string()
        } else {
            throw Exception("Request $url failed!")
        }
    }

    /**
     * 同步发送GET请求，带参数
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getWithParams(url: String, params: List<NameValuePair<*, *>>): String {
        return get(appendParams(url, params))
    }

    /**
     * 异步发送GET请求
     * @param url
     * @param callback
     */
    fun getAsync(url: String, callback: Callback) {
        val request = Request.Builder()
                .url(url)
                .build()

        mClient.newCall(request).enqueue(callback)
    }

    /**
     * 异步发送GET请求，带参数
     * @param url
     * @param params
     * @param callback
     * @throws UnsupportedEncodingException
     */
    @Throws(UnsupportedEncodingException::class)
    fun getAsyncWithParams(url: String, params: List<NameValuePair<*, *>>, callback: Callback) {
        getAsync(appendParams(url, params), callback)
    }

    /**
     * GET请求拼接参数到url上
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    @Throws(UnsupportedEncodingException::class)
    fun appendParams(url: String, params: List<NameValuePair<*, *>>): String {
        val sb = StringBuilder(url)
        sb.append("?")
        val len = params.size
        for (i in 0 until len) {
            val pair = params[i]
            sb.append(URLEncoder.encode(pair.name!!.toString(), "UTF-8"))
            sb.append("=")
            sb.append(URLEncoder.encode(pair.value!!.toString(), "UTF-8"))

            if (i != len - 1) {
                sb.append("&")
            }
        }

        return sb.toString()
    }

    /**
     * POST表单
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun postForm(url: String, params: List<NameValuePair<*, *>>): String {
        val builder = FormBody.Builder()
        for (param in params) {
            builder.add(param.name!!.toString(), param.value!!.toString())
        }
        val formBody = builder.build()

        val request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()

        val response = mClient.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body()!!.string()
        } else {
            throw Exception("Request $url failed!")
        }
    }

    /**
     * POST Json数据
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    @Throws(Exception::class)
    fun postJson(url: String, json: String): String {
        val body = RequestBody.create(MEDIA_TYPE_JSON, json)
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
        val response = mClient.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body()!!.string()
        } else {
            throw Exception("Request $url failed!")
        }
    }

    /**
     * 参数键值对
     * @param <N>
     * @param <T>
    </T></N> */
    class NameValuePair<N, T>(var name: N?, var value: T?)

    companion object {
        val instance: MyOkHttp by lazy {
            MyOkHttp()
        }

        val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8")
        val MEDIA_TYPE_XML = MediaType.parse("application/xml; charset=utf-8")
        val MEDIA_TYPE_PNG = MediaType.parse("image/png")

        private val TAG = "MyOkHttp"
    }


}

