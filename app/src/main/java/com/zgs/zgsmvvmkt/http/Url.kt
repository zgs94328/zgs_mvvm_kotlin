package com.zgs.zgsmvvmkt.http

import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain

/**
 * User: ljx
 * Date: 2020/2/27
 * Time: 23:55
 *
 */
object Url {
    @JvmField
    @DefaultDomain //设置为默认域名
    var baseUrl = "https://www.wanandroid.com"

    @JvmField
    @Domain(name = "Update")
    var update = "http://update.9158.com"


}