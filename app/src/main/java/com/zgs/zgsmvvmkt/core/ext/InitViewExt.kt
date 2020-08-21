package com.zgs.zgsmvvmkt.core.ext

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ViewFlipper
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.zgs.zgsmvvmkt.R
import com.zgs.zgsmvvmkt.ui.fragment.main.home.HomeFragment
import com.zgs.zgsmvvmkt.ui.fragment.main.mine.MineFragment
import com.zgs.zgsmvvmkt.ui.fragment.main.plaza.PlazaFragment
import com.zgs.zgsmvvmkt.ui.fragment.main.project.ProjectFragment
import com.zgs.zgsmvvmkt.ui.fragment.main.publicnumber.PublicNumberFragment
import com.zgs.zgsmvvmkt.util.SettingUtil
import com.zgs.zgsmvvmkt.util.dip2px
import com.zgs.zgsmvvmkt.weight.ColorFlipPagerTitleView
import kotlinx.android.synthetic.main.item_home_top_serach_text.view.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import java.util.*

/**
 *  @author 张国胜
 *  @time 2020/7/1
 *  @desc: 初始化控件
 */
/**
 * 初始化底部导航栏
 *
 */
fun BottomNavigationViewEx.init(navigationItemSelectedAction: (Int) -> Unit): BottomNavigationViewEx {
    enableAnimation(true)
    enableShiftingMode(false)
    enableItemShiftingMode(true)

    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction(it.itemId)
        true
    }
    return this
}
/**
 * 初始化主页viewpage
 *
 */
fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    isUserInputEnabled = false
    offscreenPageLimit = 5;
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> ProjectFragment()
                2 -> PlazaFragment()
                3 -> PublicNumberFragment()
                4 -> MineFragment()
                else -> {
                    HomeFragment()
                }
            }
        }

    }
    return this
}
/**
 * 初始化首页tablayout
 *
 */
fun MagicIndicator.initHome(
    viewpager: ViewPager2,
    titleList: ArrayList<String>
): MagicIndicator {
    var commonNavigator = CommonNavigator(context)
    commonNavigator.scrollPivotX = 0.65f
    commonNavigator.adapter = object : CommonNavigatorAdapter() {
        override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
            return ColorFlipPagerTitleView(context!!).apply {
                text = titleList[index]
                textSize = 17f
                normalColor = resources.getColor(R.color.crl_text_333333)
                selectedColor = resources.getColor(R.color.colorPrimary)
                setOnClickListener {
                    viewpager.currentItem = index
                }
            }
        }

        override fun getCount(): Int = titleList.size

        override fun getIndicator(context: Context?): IPagerIndicator {
            return LinePagerIndicator(context).apply {
                mode = LinePagerIndicator.MODE_EXACTLY
                lineHeight = dip2px(2).toFloat()
                lineWidth = dip2px(20).toFloat()
                roundRadius = dip2px(1).toFloat()
                startInterpolator = AccelerateInterpolator()
                endInterpolator = DecelerateInterpolator(2.0f)
                setColors(resources.getColor(R.color.colorPrimary))
            }
        }
    }
    navigator = commonNavigator
    viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            this@initHome.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            this@initHome.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            this@initHome.onPageSelected(position)
        }
    })
    return this
}

/**
 * 初始化首页热门搜索词条
 *
 */
fun ViewFlipper.init(
    contents: ArrayList<String>,
    viewFlipperSelectedAction: (Int) -> Unit
): ViewFlipper {
    contents.forEach {
        var textView = View.inflate(context, R.layout.item_home_top_serach_text, null)
        textView.tv_serach_content.text = it
        this.addView(textView)
    }
    setOnClickListener {
        viewFlipperSelectedAction.invoke(displayedChild)
    }
    return this
}
/**
 * 初始化普通的toolbar 只设置标题
 */
fun Toolbar.init(titleStr: String = ""): Toolbar {
    title = titleStr
    return this
}
