package com.zgs.zgsmvvmkt.http.manager

import android.app.Application
import com.zgs.zgsmvvmkt.BuildConfig
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.HttpsUtils
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * User: ljx
 * Date: 2019-11-26
 * Time: 20:44
 *
 * @author gs-pc
 */
object RxHttpManager {
    fun init(context: Application?) {
        val file = File(context!!.externalCacheDir, "RxHttpCookie")
        val sslParams = HttpsUtils.getSslSocketFactory()
        val client = OkHttpClient.Builder()
            .cookieJar(CookieStore(file))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(sslParams!!.sSLSocketFactory, sslParams.trustManager) //添加信任证书
            .hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }) //忽略host验证
//            .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
//            .addInterceptor(new RedirectInterceptor())
//            .addInterceptor(new TokenInterceptor())
            .build()
        //RxHttp初始化，自定义OkHttpClient对象，非必须
        RxHttp.init(client, BuildConfig.DEBUG)
        //设置缓存策略，非必须
        val cacheFile = File(context.externalCacheDir, "RxHttpCache")
        RxHttpPlugins.setCache(
            cacheFile,
            1000 * 100.toLong(),
            CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE
        )
        RxHttpPlugins.setExcludeCacheKeys("time") //设置一些key，不参与cacheKey的组拼
        //设置数据解密/解码器，非必须
//        RxHttp.setResultDecoder(s -> s);
//设置全局的转换器，非必须
//        RxHttp.setConverter(FastJsonConverter.create());
//设置公共参数，非必须
        RxHttp.setOnParamAssembly { p: Param<*>? ->
            /*根据不同请求添加不同参数，子线程执行，每次发送请求前都会被回调
            如果希望部分请求不回调这里，发请求前调用Param.setAssemblyEnabled(false)即可
             */
            val method = p!!.method
            if (method!!.isGet) { //Get请求
            } else if (method.isPost) { //Post请求
            }
            p.add("versionName", "1.0.0") //添加公共参数
                .add("time", System.currentTimeMillis())
                .addHeader("deviceType", "android") //添加公共请求头
        }
    }
}