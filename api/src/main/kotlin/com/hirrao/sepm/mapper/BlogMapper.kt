package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.Blog
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.*

@Mapper
interface BlogMapper : BaseMapper<Blog?> {
    @Select("select * from blog where blogId = #{blogId}")
    fun findByBlogId(blogId: Long?): Blog?

    @Select("select * from blog where uid = #{uid}")
    fun findByUid(uid: Int): List<Blog?>?

    @Select("select * from blog where title = #{title}")
    fun findByTitle(title: String?): List<Blog?>?

    @Insert("insert into blog(blogId, content, createTime, updateTime, uid, title) values(#{blogId}, #{content}, #{createTime}, #{updateTime}, #{uid}, #{title})")
    fun addBlog(blogId: Long?, content: String?, createTime: String?, updateTime: String?, uid: Int, title: String?)

    @Update("update blog set content=#{content}, updateTime=#{updateTime}, title=#{title} where blogId=#{blogId}")
    fun update(blogId: Long?, content: String?, updateTime: String?, title: String?)

    @Delete("DELETE FROM blog WHERE blogId = #{blogId}")
    fun delete(blogId: Long?)
}
