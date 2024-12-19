package com.hirrao.sepm.service

import com.hirrao.sepm.entity.Article
import com.baomidou.mybatisplus.core.metadata.IPage
import org.springframework.stereotype.Service

@Service
interface ArticleService {
    //返回全部文章
    fun findAll(): List<Article?>?

    //根据文章id查询文章
    fun findById(id: Long): Article?

    //添加文章
    fun addArticle(title: String?, description: String?, image: String?, content: String?)


    fun deleteArticle(id: Long)

    fun modifyArticleInfo(id: Long, title: String?, description: String?, image: String?, content: String?)

    fun articlesInfo(curPage: Int, size: Int): IPage<Article?>?

    fun searchArticleByCondition(
        curPage: Int,
        size: Int,
        searchCondition: String?,
        conditionValue: String?
    ): IPage<Article?>?
}
