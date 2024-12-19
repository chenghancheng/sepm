package com.hirrao.sepm.utils

import io.jsonwebtoken.Jwts
import javax.crypto.SecretKey

internal object CONST {
    val key: SecretKey = Jwts.SIG.HS256.key().build()
}