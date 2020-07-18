package com.zgs.zgsmvvmkt.http

/**
 *  @author 张国胜
 *  @time 2020/7/4
 *  @desc: 请求地址
 */
object Api{
    fun getHomeListUrl(page:Int): String{
        return "article/list/$page/json"
    }
}