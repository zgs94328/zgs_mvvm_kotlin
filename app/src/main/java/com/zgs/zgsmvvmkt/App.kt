package com.zgs.zgsmvvmkt

import android.app.Application
import android.content.Context
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.zgs.zgsmvvmkt.di.viewModelModule
import com.zgs.zgsmvvmkt.http.manager.RxHttpManager
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.EmptyCallback
import me.hgj.jetpackmvvm.demo.app.weight.loadCallBack.ErrorCallback
import com.zgs.zgsmvvmkt.util.loadCallBack.LoadingCallback
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *  @author 张国胜
 *  @time 2020/6/24
 *  @desc:
 */
class App : Application() {
    companion object {
        private var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        _context = this
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(viewModelModule)
        }
        RxHttpManager.init(this)
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()

    }

}