package com.hirrao.sepm.entity


data class Result(
    val code: Int? = null, //状态码，0表示成功，-1表示失败
    val message: String? = null,//提示信息
    val data: Any? = null, //响应数据，比如token
)

