package com.zgs.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *  @author 张国胜
 *  @time 2020/5/28
 *  @desc:
 */
abstract class BaseActivity : AppCompatActivity(){
    protected lateinit var mBinding: ViewDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, getLayoutResId())
        mBinding.lifecycleOwner =this
        initView()
        initData()
        startObserve()
    }
    abstract fun getLayoutResId() : Int
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}