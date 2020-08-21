package com.zgs.baselibrary.ext.view

import android.view.View
import com.zgs.baselibrary.util.Tip

/**
 *  @author 张国胜
 *  @time 2020/8/12
 *  @desc:
 */
/**
 * 防止重复点击事件 默认0.5秒内不可重复点击
 * @param interval 时间间隔 默认0.5秒
 * @param action 执行方法
 */
var lastClickTime = 0L
fun setOnClickNoRepeatListener(vararg v: View?, interval: Long = 500, block: View.() -> Unit) {
    val listener = View.OnClickListener {

        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            Tip.show("请勿重复点击")
            return@OnClickListener
        }
        lastClickTime = currentTime
        it.block()
    }
    v.forEach { it?.setOnClickListener(listener) }
}



