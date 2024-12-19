package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.BlogService
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.SnowFlake
import com.hirrao.sepm.utils.ThreadLocalUtil.get
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/profile")
class BlogController @Autowired private constructor(private val blogService: BlogService) {
    private val snowFlake = SnowFlake(1, 1)

    @GetMapping("/userBlogInfo")
    fun userBlogInfo(): Result {
        val user = get()
        val uid = user.uid
        val blog = blogService.findByUid(uid)
        return success(blog)
    }

    @PostMapping("/blogInfo")
    fun blogInfo(@RequestBody map: Map<String?, String>): Result {
        val blogId = map["blogId"]!!.toLong()
        val blog = blogService.findByBlogId(blogId)
        return success(blog)
    }

    @GetMapping("/userInfo")
    fun userInfo(): Result {
        val user = get()
        return success(user)
    }

    @PostMapping("/add")
    fun add(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val blogId = snowFlake.nextId()
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = now.format(formatter)
        val content = map["content"]
        val title = map["title"]
        blogService.addBlog(blogId, content, time, time, uid, title)
        return success()
    }

    @PostMapping("/search")
    fun searchUserByCondition(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        val user = get()
        val uid = user.uid
        return success(blogService.search(curPage, size, uid))
    }

    @PostMapping("/delete")
    fun delete(@RequestBody map: Map<String?, String>): Result {
        val blogId = map["blogId"]!!.toLong()
        blogService.delete(blogId)
        return success()
    }

    @PostMapping("/update")
    fun update(@RequestBody map: Map<String?, String>): Result {
        val blogId = map["blogId"]!!.toLong()
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = now.format(formatter)
        val content = map["content"]
        val title = map["title"]
        blogService.update(blogId, content, time, title)
        return success()
    }
}
