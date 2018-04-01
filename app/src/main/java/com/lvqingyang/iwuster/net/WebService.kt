package com.lvqingyang.iwuster.net

import com.lvqingyang.frame.tool.Md5Util
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.text.SimpleDateFormat
import java.util.*


/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2018/1/12
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
private val endPoint = "http://jwxt.wust.edu.cn/whkjdx/services/whkdapp"
private val key = "webservice_whkdapp"
private val nameSpace = "http://webservices.qzdatasoft.com"
private val soapAction = "http://webservices.qzdatasoft.com/"

fun getResult(funcation: String, soapObject: SoapObject, n: Int): String {
    val localDate = Date()
    val str1 = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(localDate)
    val str2 = Md5Util.MD5("webservice_whkdapp$str1").substring(2).toLowerCase()
    val localStringBuilder = StringBuilder().append("in")
    soapObject.addProperty("in$n", str1)
    soapObject.addProperty("in${n +1}", str2)
    val localSoapSerializationEnvelope = SoapSerializationEnvelope(110)
    localSoapSerializationEnvelope.bodyOut = soapObject
    localSoapSerializationEnvelope.dotNet = true
    localSoapSerializationEnvelope.setOutputSoapObject(soapObject)
    HttpTransportSE(endPoint).call(soapAction + funcation, localSoapSerializationEnvelope)
    return (localSoapSerializationEnvelope.bodyIn as SoapObject).getProperty("out").toString()
}

internal  fun  getYxkc(xh: String, xnxq: String): String {
    val localSoapObject = SoapObject(nameSpace, "getyxkclb")
    localSoapObject.addProperty("in0", xh)
    localSoapObject.addProperty("in1", xnxq)
    return getResult("getyxkclb", localSoapObject, 2)
}

internal fun getXnxq(): String {
    return getResult("getpjxnxq", SoapObject(nameSpace, "getpjxnxq"), 0)
}

internal fun getXscjcx(xh: String): String {
    val localSoapObject = SoapObject(nameSpace, "getxscj")
    localSoapObject.addProperty("in0", xh)
    return getResult("getxscj", localSoapObject, 1)
}