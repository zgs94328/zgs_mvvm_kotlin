package com.zgs.zgsmvvmkt.weight

import android.content.Context
import android.graphics.Typeface
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView
import java.lang.ref.Reference

/**
 * 选中字体变粗
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
class ColorFlipPagerTitleView(context: Context) : SimplePagerTitleView(context) {
    override fun onSelected(index: Int, totalCount: Int) {
        setTextColor(mSelectedColor)
        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        setTextColor(mNormalColor)
        typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    }

}
