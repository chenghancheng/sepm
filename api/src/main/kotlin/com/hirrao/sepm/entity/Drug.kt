package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class Drug(
    @TableId(value = "drugId") //@TableField("drugId")
    val drugId: Long? = null,
    val uid: Long? = null,

    @TableField("drugName") private val drugName: String? = null,
    val frequency: String? = null,
    val unit: String? = null,
    val dosage: Float? = null,
    @TableField("isActive") val isActive: Int = 0,
)