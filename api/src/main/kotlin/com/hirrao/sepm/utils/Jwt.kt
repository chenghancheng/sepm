package com.hirrao.sepm.utils

import com.hirrao.sepm.entity.User
import io.jsonwebtoken.Jwts
import java.util.*

/**
 * 创建token
 */
object Jwt {
    /**
     * 创建token
     *
     * @param user 直接传入User对象
     * @return 生成的经过压缩的token
     */

    fun createToken(user: User): String {
        val builder = Jwts.builder().id(user.uid.toString()).subject(user.userName).issuedAt(Date()).signWith(CONST.key)
            .expiration(
                Date(
                    System.currentTimeMillis() + 1000 * 60 * 60 * 2
                )
            )
        return builder.compact()
    }


    fun parseToken(token: String): String {
        return Jwts.parser().verifyWith(CONST.key).build().parseSignedClaims(token).payload.id
    }
}
