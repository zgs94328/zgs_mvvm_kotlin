package com.zgs.zgsmvvmkt.weight

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Paint
import android.util.AttributeSet
import com.zgs.zgsmvvmkt.R
import kotlin.properties.Delegates


/**
 *  @author 张国胜
 *  @time 2020/5/29
 *  @desc:
 */
class UnderlineEditText : androidx.appcompat.widget.AppCompatEditText {
    private var paint = Paint()
    var lineColor by Delegates.notNull<Int>()
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        // 获取控件资源
        val typedArray =
            context?.obtainStyledAttributes(attrs, R.styleable.UnderlineEditText)
        typedArray?.let { initUnderline(it) }
    }
    /**
     * 初始化分割线（颜色、粗细、位置）
     */
    private fun initUnderline(typedArray: TypedArray) {
        var underLineColor_default = resources.getColor(R.color.colorPrimary)
        typedArray.getColor(R.styleable.UnderlineEditText_underlineColor,underLineColor_default)
        paint?.run {
            color = underLineColor_default

        }

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

}