package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableId

data class HeartRate(
    @TableId(value = "hrlId") val hrlId: Long? = null,
    val uid: Long? = null,
    val heartRate: Float? = null,
    val recordTime: String? = null,
)