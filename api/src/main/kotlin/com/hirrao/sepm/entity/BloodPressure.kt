package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDate

@TableName("bloodPressureLog")
data class BloodPressure(
    @TableId(value = "bplId") val bplId: Long? = null,
    val uid: Long? = null,
    @TableField("SBP") val SBP: Float? = null,
    @TableField("DBP") val DBP: Float? = null,

    @TableField("recordTime") val recordTime: LocalDate? = null,

    @TableField("riskLevel") val riskLevel: String? = null,
    val classification: String? = null
)