package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class UserIntro(
    @TableId(value = "uid") val uid: Long? = null,

    @TableField("intro") val intro: String? = null,
)
