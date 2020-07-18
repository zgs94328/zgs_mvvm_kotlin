package com.zgs.zgsmvvmkt.di

import com.zgs.zgsmvvmkt.viewmodel.HomeModel
import com.zgs.zgsmvvmkt.viewmodel.RegistAndLoginModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  @author 张国胜
 *  @time 2020/6/24
 *  @desc:
 */
val viewModelModule = module {
    viewModel { RegistAndLoginModel() }
    viewModel { HomeModel() }
}