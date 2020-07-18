package com.zgs.zgsmvvmkt.http.entity

/**
 * @author 张国胜
 * @time 2020/7/5
 * @desc:
 */
class BaseResponse<T> {
    var code = 0
        private set
    var msg: String? = null
        private set
    var data: T? = null
        private set

    fun setErrorCode(errorCode: Int) {
        code = errorCode
    }

    fun setErrorMsg(errorMsg: String?) {
        msg = errorMsg
    }

    fun setData(data: T) {
        this.data = data
    }
}