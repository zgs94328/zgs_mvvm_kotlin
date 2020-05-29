package com.zgs.zgsmvvmkt.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.zgs.zgsmvvmkt.R


import java.lang.reflect.InvocationTargetException
import kotlin.math.roundToInt


object SettingUtil {

    /**
     * 获取当前主题颜色
     */
    fun getColor(context: Context): Int {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else {
            color
        }

    }

    /**
     * 设置主题颜色
     */
    fun setColor(context: Context, color: Int) {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        setting.edit().putInt("color", color).apply()
    }



}
