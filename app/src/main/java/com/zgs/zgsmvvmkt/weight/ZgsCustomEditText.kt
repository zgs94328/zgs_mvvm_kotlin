package com.zgs.zgsmvvmkt.weight

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import com.zgs.zgsmvvmkt.util.dip2px
import com.zgs.zgsmvvmkt.R
import kotlin.properties.Delegates


/**
 *  @author 张国胜
 *  @time 2020/5/29
 *  @desc: 自定义下划线
 */
class ZgsCustomEditText : androidx.appcompat.widget.AppCompatEditText {
    private val defaultColor: Int = R.color.crl_text_999999
    private var paint = Paint()
    private var paintColor = resources.getColor(defaultColor)
    // 分割线变量
    private var lineDefaultColor = resources.getColor(defaultColor) // 分割线默认颜色
    private var lineUnClickColor = resources.getColor(defaultColor) // 分割线未点击颜色
    private var lineClickColor = resources.getColor(defaultColor) // 分割线点击颜色
    private var lineWidth = 1 // 分割线宽度
    private var lineMarginTop by Delegates.notNull<Int>()  //分割线距离文字间距
    //右侧图标
    private var icRightResId: Int = 0 // 右侧图标 资源ID
    private  var rightDrawable: Drawable? = null
    //右侧图标位置及大小
    private var rightIconX: Int = 0
    private var rightIconY: Int = 0
    private var rightIconWidth: Int = 0
    private var rightIconHeight: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        // 获取控件资源
        val typedArray =
            context?.obtainStyledAttributes(attrs, R.styleable.ZgsCustomEditText)
        typedArray?.let {
            initUnderline(it)
            initRightIcon(it)
            initLeftIcon(it)
        }
    }

    /**
     * 初始化左侧图标
     * */
    private fun initLeftIcon(it: TypedArray) {

    }

    /**
     * 初始化右侧图标
     * */
    private fun initRightIcon(it: TypedArray) {
        // 1. 获取资源ID
        icRightResId =
            it.getResourceId(R.styleable.ZgsCustomEditText_right_icon, -1)
        if (icRightResId == -1) return
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        rightDrawable = resources.getDrawable(icRightResId)
        // 3. 设置图标大小
        // 起点(x，y)、宽= left_width、高 = left_height
        rightIconX = it.getInteger(R.styleable.ZgsCustomEditText_right_icon_x, 0)
        rightIconY = it.getInteger(R.styleable.ZgsCustomEditText_right_icon_y, 0)
        rightIconWidth = dip2px(it.getInteger(R.styleable.ZgsCustomEditText_right_icon_width, 20))
        rightIconHeight = dip2px(it.getInteger(R.styleable.ZgsCustomEditText_right_icon_height, 20))
        rightDrawable?.setBounds(rightIconX, rightIconY, rightIconWidth, rightIconHeight)
//        setCompoundDrawables(null, null, rightDrawable, null)
    }


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 初始化分割线（颜色、粗细、位置）
     */
    private fun initUnderline(typedArray: TypedArray) {
        //默认分割线颜色
        lineDefaultColor = typedArray.getColor(
            R.styleable.ZgsCustomEditText_line_default_color,
            resources.getColor(defaultColor)
        )
        //分割线未点击颜色
        lineUnClickColor =
            typedArray.getColor(
                R.styleable.ZgsCustomEditText_line_unclick_color,
                lineDefaultColor
            )
        //分割线点击颜色
        lineClickColor =
            typedArray.getColor(
                R.styleable.ZgsCustomEditText_line_click_color,
                lineDefaultColor
            )
        //分割线宽度颜
        lineWidth = dip2px(typedArray.getInt(R.styleable.ZgsCustomEditText_line_width, 1))
        //分割线于文字的间距
        lineMarginTop = dip2px(typedArray.getInt(R.styleable.ZgsCustomEditText_line_margin_top, 1))

        //赋值画笔颜色，如果设置未点击颜色，则默认去未点击分割线颜色，否则取默认分割线颜色
        paintColor = lineUnClickColor
        paint?.run {
            color = paintColor
            strokeWidth = lineWidth.toFloat()

        }
        background = null
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        setDeleteIconVisible(focused && length() > 0, focused)
        // focused = 是否获得焦点
        // 同样根据setDeleteIconVisible（）判断是否要显示删除图标->>关注1
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        setDeleteIconVisible(hasFocus() && length() > 0, hasFocus())
    }

    /**
     * 作用：对删除图标区域设置为"点击 即 清空搜索框内容"
     * 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
     */
    override fun onTouchEvent(event: MotionEvent): Boolean { // 原理：当手指抬起的位置在删除图标的区域，即视为点击了删除图标 = 清空搜索框内容
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                val drawable: Drawable? = rightDrawable
                if (drawable != null && event.x <= width - paddingRight && event.x >= width - paddingRight - drawable.bounds.width()
                ) { // 判断条件说明
// event.getX() ：抬起时的位置坐标
// getWidth()：控件的宽度
// getPaddingRight():删除图标图标右边缘至EditText控件右边缘的距离
// 即：getWidth() - getPaddingRight() = 删除图标的右边缘坐标 = X1
// getWidth() - getPaddingRight() - drawable.getBounds().width() = 删除图标左边缘的坐标 = X2
// 所以X1与X2之间的区域 = 删除图标的区域
// 当手指抬起的位置在删除图标的区域（X2=<event.getX() <=X1），即视为点击了删除图标 = 清空搜索框内容
                    setText("")
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun setDeleteIconVisible(b: Boolean, focused: Boolean) {
        setCompoundDrawables(
            null, null,
            if (b) rightDrawable else null, null
        )
        paintColor = if (focused) lineClickColor else lineUnClickColor
        paint?.let {
            it.color = paintColor
        }
        invalidate()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        // 绘制分割线
// 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
// 解决方案：线的长度 = 控件长度 + 延伸后的长度
        val x = this.scrollX // 获取延伸后的长度

        val w = this.measuredWidth // 获取控件长度


        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        canvas?.drawLine(
            0f, (this.measuredHeight - lineWidth).toFloat(), w + x.toFloat(),
            (this.measuredHeight - lineWidth).toFloat(), paint
        )
    }
}