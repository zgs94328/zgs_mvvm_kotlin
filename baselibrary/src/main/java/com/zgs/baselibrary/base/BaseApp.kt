package com.zgs.baselibrary.base

import android.app.Application
import android.content.Context

/**
 *  @author 张国胜
 *  @time 2020/8/12
 *  @desc:
 */
open class BaseApp : Application() {
    companion object {
        private var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        _context = this

    }
}