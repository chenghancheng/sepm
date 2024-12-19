package com.hirrao.sepm.service

import com.hirrao.sepm.entity.Blog
import com.baomidou.mybatisplus.core.metadata.IPage
import org.springframework.stereotype.Service

@Service
interface BlogService {
    fun findByBlogId(bolgId: Long?): Blog?

    fun findByUid(uid: Int): List<Blog?>?

    fun findByTitle(title: String?): List<Blog?>?

    fun addBlog(blogId: Long?, content: String?, createTime: String?, updateTime: String?, uid: Int, title: String?)

    fun update(blogId: Long?, content: String?, updateTime: String?, title: String?)

    fun delete(blogId: Long?)

    fun search(curPage: Int, size: Int, uid: Int): IPage<Blog?>?
}
