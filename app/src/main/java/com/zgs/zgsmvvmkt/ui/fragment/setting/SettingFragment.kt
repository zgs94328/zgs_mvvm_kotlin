package com.zgs.zgsmvvmkt.ui.fragment.main.setting

import android.os.Bundle
import com.zgs.baselibrary.base.BaseFragment
import com.zgs.zgsmvvmkt.R

/**
 *  @author 张国胜
 *  @time 2020/10/12
 *  @desc:
 */
class SettingFragment: BaseFragment() {


    override fun layoutId(): Int = R.layout.fragment_setting

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
    }

    @Throws(Exception::class)
    fun decodeHex(data: CharArray): ByteArray? {
        val len = data.size
        if (len and 0x1 != 0) throw Exception("Odd number of characters.")
        val out = ByteArray(len shr 1)
        var i = 0
        var j = 0
        while (j < len) {
            var f: Int = toDigit(data[j], j) shl 4
            j++
            f = f or toDigit(data[j], j)
            j++
            out[i] = (f and 0xFF).toByte()
            i++
        }
        return out
    }
}