package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.UserIntroService
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.ThreadLocalUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/intro")
class UserIntroController @Autowired constructor(private val userIntroService: UserIntroService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/get")
    fun get(): Result {
        val user = ThreadLocalUtil.get()
        val uid = user.uid
        var userIntro = userIntroService.findUserIntro(uid)
        if (userIntro == null) userIntroService.setUserIntro(uid, "")
        userIntro = userIntroService.findUserIntro(uid)
        return success(userIntro)
    }

    @PostMapping("/update")
    fun update(@RequestBody map: Map<String?, String>): Result {
        logger.debug("intro/update接受请求 {}", map)
        val uid = map["uid"]!!.toLong()
        val userIntro = map["intro"]
        userIntroService.updateUserIntro(uid, userIntro)
        return success("修改成功")
    }
}
