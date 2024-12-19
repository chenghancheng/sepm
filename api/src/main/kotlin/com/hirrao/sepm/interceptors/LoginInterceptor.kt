package com.hirrao.sepm.interceptors

import com.hirrao.sepm.service.UserService
import com.hirrao.sepm.utils.Jwt.parseToken
import com.hirrao.sepm.utils.ThreadLocalUtil.remove
import com.hirrao.sepm.utils.ThreadLocalUtil.set
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

//拦截器
@Component
class LoginInterceptor @Autowired constructor(private val userService: UserService) : HandlerInterceptor {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            return true
        }
        println(request.getHeader("Authorization"))
        //获取请求头中的token
        val token = request.getHeader("Authorization")
        logger.debug("token={}", token)
        //先验证token
        try {
            val uid = parseToken(token)
            println(uid)
            val user = userService.findByUid(uid.toInt())
            set(user!!)
            return true
        } catch (e: Exception) {
            //响应状态码为401
            response.status = 401
            return false
        }
    }

    override fun afterCompletion(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any, @Nullable ex: java.lang.Exception?
    ) {
        //清除ThreadLocal中的用户信息
        remove()
    }
}