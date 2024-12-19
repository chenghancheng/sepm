package com.hirrao.sepm.entity

import com.baomidou.mybatisplus.annotation.TableName
import org.springframework.data.annotation.Id


@TableName("articles")
data class Article(
    @Id var id: Long? = null,

    var title: String? = null,
    var description: String? = null,
    var image: String? = null,
    var content: String? = null // getters and setters,
)