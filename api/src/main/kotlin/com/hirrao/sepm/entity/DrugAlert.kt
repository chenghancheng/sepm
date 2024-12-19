package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.sql.Date
import java.sql.Time

@TableName("DrugAlert")
data class DrugAlert(
    @TableId(value = "alertId") //@TableField("alertId")
    val alertId: Long? = null,
    val uid: Long? = null,

    @TableField("drugId") val drugId: Long? = null,

    @TableField("alertTime") val alertTime: Time? = null,

    @TableField("eatTime") val eatTime: Date? = null,
)