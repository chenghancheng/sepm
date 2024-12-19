package com.hirrao.sepm.controller

import com.hirrao.sepm.dto.LoginDto
import com.hirrao.sepm.dto.RegisterDto
import com.hirrao.sepm.dto.ResetPasswordDto
import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.UserService
import com.hirrao.sepm.utils.IdGen
import com.hirrao.sepm.utils.Jwt.createToken
import com.hirrao.sepm.utils.Result.error
import com.hirrao.sepm.utils.Result.success
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.DigestUtils
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user/auth")
class AuthController @Autowired constructor(
    private val userService: UserService
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val idGen = IdGen(8)

    @PostMapping("/message")
    fun message(@RequestBody map: Map<String?, String?>?): Result {
        return success()
    }


    //查找手机号或者名字是否已被占用
    @GetMapping("/find")
    fun find(@RequestParam userName: String, @RequestParam phoneNumber: String): Result {
        if (userName.isBlank() || phoneNumber.isBlank()) {
            return error(101, "非法用户名或手机号")
        }
        val user = userService.findByUsername(userName)
        if (user != null) {
            return error(102, "用户名或手机号已被占用")
        }
        val user2 = userService.findByPhoneNumber(phoneNumber)
        if (user2 != null) {
            return error(103, "用户名或手机号已被占用")
        }
        return success()
    }

    @PostMapping("/register")
    fun register(@RequestBody data: RegisterDto): Result {
        if (data.userName.isBlank() || data.userPassword.isBlank() || !data.userName.matches("^[a-zA-Z0-9_]{3,20}$".toRegex()) || !data.userPassword.matches(
                "^[a-zA-Z0-9_]{6,20}$".toRegex()
            )
        ) {
            return error(101, "非法用户名或密码")
        }
        val user = userService.findByUsername(data.userName)
        if (user != null) {
            return error(102, "用户名已被占用")
        }
        var uid: Int
        do {
            uid = idGen.gen()
        } while (userService.findByUid(uid) != null)
        val password = DigestUtils.md5DigestAsHex(data.userPassword.toByteArray())
        userService.register(uid, data.userName, password, data.phoneNumber)
        logger.info("注册成功 用户名:{} 手机号:{}", data.userName, data.phoneNumber)
        return success()
    }

    @PostMapping("/messageSend")
    fun messageSend(): Result {
        return success()
    }

    @PostMapping("/login")
    fun login(@RequestBody data: LoginDto): Result {
        if (data.userName.isBlank() || data.userPassword.isBlank()) {
            return error(104, "用户名或密码错误")
        }
        val user = userService.findByUsername(data.userName)
        if (user == null) {
            return error(104, "用户名或密码错误")
        } else {
            if (data.userPassword == user.userPassword) {
                //密码正确，根据用户的uid和用户名生成token
                val tokens = createToken(user)

                data class Data(
                    val permission: Int = user.permission, val token: String = tokens,
                )

                val ans = Data()
                if (ans.permission == -1) return error(106, "该用户已被封禁")
                logger.info("${success(ans)}")
                return success(ans)
            } else {
                return error(104, "用户名密码错误")
            }
        }
    }

    @PostMapping("/resetPassword")
    fun resetPassword(@RequestBody data: ResetPasswordDto): Result {
        if (data.phoneNumber.isBlank() || data.messageCode < 1000 || data.newPassword.matches("^[a-zA-Z0-9_]{6,20}$".toRegex())) {
            return error(101, "非法手机号或验证码或密码")
        }
        val user = userService.findByPhoneNumber(data.phoneNumber)
        val password = DigestUtils.md5DigestAsHex(data.newPassword.toByteArray())
        if (user == null) {
            return error(105, "用户不存在")
        }
        userService.updatePassword(user.uid, password)
        logger.info("重置密码成功 用户名:{} 手机号:{}", user.userName, data.phoneNumber)
        return success()
    }
}
