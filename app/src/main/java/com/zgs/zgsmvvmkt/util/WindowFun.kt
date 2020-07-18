package com.zgs.zgsmvvmkt.util

import android.content.res.Resources
import android.widget.Toast
import com.zgs.zgsmvvmkt.App

/**
 *  @author 张国胜
 *  @time 2020/6/16
 *  @desc:扩展方法
 */
fun Any.dip2px(dpValue: Int): Int {
    var scale = Resources.getSystem().displayMetrics.density
    return ((dpValue * scale + 0.5f).toInt())
}

fun px2dip(pxValue: Int): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

