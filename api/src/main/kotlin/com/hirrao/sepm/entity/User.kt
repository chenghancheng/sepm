package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId

//JsonIgnore注解是jackson包中的注解，用于返回json数据时，忽略该字段
data class User(
    @TableId(value = "uid") var uid: Int,

    @TableField("userName") var userName: String? = null,

    @TableField("phoneNumber") var phoneNumber: String? = null,

    @TableField("userPassword") var userPassword: String? = null,
    var sex: String? = null,
    var birthday: String? = null,
    var permission: Int = 0,
)
