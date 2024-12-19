package com.hirrao.sepm.utils

import com.hirrao.sepm.entity.Result

object Result {
    fun success(data: Any?): Result {
        return Result(0, "success", data)
    }


    fun success(): Result {
        return Result(0, "success", null)
    }


    fun error(code: Int, message: String?): Result {
        return Result(code, message, null)
    }

}