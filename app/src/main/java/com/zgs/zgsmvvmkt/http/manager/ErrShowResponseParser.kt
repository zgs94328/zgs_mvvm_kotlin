package com.zgs.zgsmvvmkt.http.manager

import com.zgs.zgsmvvmkt.http.entity.AppException
import com.zgs.zgsmvvmkt.http.entity.BaseResponse
import com.zgs.baselibrary.util.Tip
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.parse.AbstractParser
import java.lang.Exception
import java.lang.reflect.Type


/**
 *  @author 张国胜
 *  @time 2020/6/28
 *  @desc: 自定义错误处理
 */
@Parser(name = "ErrShowResponse")
open class ErrShowResponseParser<T> : AbstractParser<T> {
    /**
     * 此构造方法适用于任意Class对象，但更多用于带泛型的Class对象，如：List<Student>
     *
     * 用法:
     * Java: .asParser(new ResponseParser<List<Student>>(){})
     * Kotlin: .asParser(object : ResponseParser<List<Student>>() {})
     *
     * 注：此构造方法一定要用protected关键字修饰，否则调用此构造方法将拿不到泛型类型
     */
    protected constructor() : super()

    /**
     * 此构造方法仅适用于不带泛型的Class对象，如: Student.class
     *
     * 用法
     * Java: .asParser(new ResponseParser<>(Student.class))   或者  .asResponse(Student.class)
     * Kotlin: .asParser(ResponseParser(Student::class.java)) 或者  .asResponse<Student>()
     */
    constructor(type: Type) : super(type)

    override fun onParse(response: okhttp3.Response): T {
        try {
            val type: Type = ParameterizedTypeImpl[BaseResponse::class.java, mType] //获取泛型类型
            val data: BaseResponse<T> = convert(response, type)
            var t = data.data //获取data字段
            if (t == null && mType === String::class.java) {
                /*
                 * 考虑到有些时候服务端会返回：{"errorCode":0,"errorMsg":"关注成功"}  类似没有data的数据
                 * 此时code正确，但是data字段为空，直接返回data的话，会报空指针错误，
                 * 所以，判断泛型为String类型时，重新赋值，并确保赋值不为null
                 */
                @Suppress("UNCHECKED_CAST")
                t = data.msg as T
            }
            if (data.code != 0 || t == null) { //code不等于0，说明数据不正确，抛出异常
                throw AppException(data.code, data.msg)
            }
            return t

        } catch (e: Exception) {
            var appException =
                ExceptionHandle.handleException(e)
            Tip.show(appException.errorMsg)
            throw appException
        }

    }
}