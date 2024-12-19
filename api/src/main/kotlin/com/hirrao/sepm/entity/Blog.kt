package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

data class Blog(
    @TableId(value = "blogId") val blogId: Long? = null,

    @TableField("content") val content: String? = null,

    @TableField("createTime") val createTime: String? = null,

    @TableField("updateTime") val updateTime: String? = null,
    val uid: Long? = null,
    val title: String? = null,
)
