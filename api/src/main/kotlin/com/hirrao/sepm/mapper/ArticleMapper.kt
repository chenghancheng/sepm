package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.Article
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface ArticleMapper : BaseMapper<Article?> {
    @Select("SELECT * FROM articles")
    fun findAll(): List<Article?>?

    @Select("SELECT * FROM articles WHERE id = #{id}")
    fun findById(id: Long): Article?

    @Select("SELECT * FROM articles WHERE title LIKE CONCAT('%', #{title}, '%')")
    fun findByTitle(title: String?): List<Article?>?

    @Select("SELECT * FROM articles WHERE description LIKE CONCAT('%', #{description}, '%')")
    fun findByDescription(description: String?): List<Article?>?

    @Insert("INSERT INTO articles(title, description, image, content) VALUES(#{title}, #{description}, #{image}, #{content})")
    fun addArticle(title: String?, description: String?, image: String?, content: String?)

    @Update("UPDATE articles SET title = #{title}, description = #{description}, image = #{image}, content = #{content} WHERE id = #{id}")
    override fun updateById(article: Article?): Int

    @Delete("DELETE FROM articles WHERE id = #{id}")
    fun deleteById(id: Long)
}
