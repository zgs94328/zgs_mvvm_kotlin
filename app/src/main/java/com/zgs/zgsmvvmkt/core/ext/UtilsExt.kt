package com.zgs.zgsmvvmkt.core.ext

import android.text.Html
import android.text.Spanned

/**
 *  @author 张国胜
 *  @time 2020/7/9
 *  @desc:
 */
fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }
}