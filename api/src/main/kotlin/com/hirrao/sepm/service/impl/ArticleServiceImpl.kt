package com.hirrao.sepm.service.impl

import com.hirrao.sepm.entity.Article
import com.hirrao.sepm.mapper.ArticleMapper
import com.hirrao.sepm.service.ArticleService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl @Autowired constructor(private val articleMapper: ArticleMapper) : ArticleService {
    override fun findAll(): List<Article?>? {
        return articleMapper.findAll()
    }

    override fun findById(id: Long): Article? {
        return articleMapper.findById(id)
    }

    override fun addArticle(title: String?, description: String?, image: String?, content: String?) {
        articleMapper.addArticle(title, description, image, content)
    }


    override fun deleteArticle(id: Long) {
        articleMapper.deleteById(id)
    }

    override fun modifyArticleInfo(id: Long, title: String?, description: String?, image: String?, content: String?) {
        val article = Article()
        article.id = id
        article.title = title
        article.description = description
        article.image = image
        article.content = content
        articleMapper.updateById(article)
    }

    override fun articlesInfo(curPage: Int, size: Int): IPage<Article?>? {
//        PageHelper.startPage(curPage, size);
//        List<Article> articles = articleMapper.findAll();
//        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        val page = Page<Article>(curPage.toLong(), size.toLong())
        return articleMapper.selectPage(page, null)
    }

    override fun searchArticleByCondition(
        curPage: Int, size: Int, searchCondition: String?, conditionValue: String?
    ): IPage<Article?>? {
//        PageHelper.startPage(curPage, size);
//        List<Article> articles;
//        switch (searchCondition) {
//            case "title":
//                articles = articleMapper.findByTitle(conditionValue);
//                break;
//            case "description":
//                articles = articleMapper.findByDescription(conditionValue);
//                break;
//            default:
//                articles = new ArrayList<>();
//        }
//        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        val page = Page<Article>(curPage.toLong(), size.toLong())
        val queryWrapper = QueryWrapper<Article>()
        queryWrapper.eq(searchCondition, conditionValue)


        return articleMapper.selectPage(page, queryWrapper)
    }
}

