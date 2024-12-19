package com.hirrao.sepm.service

import org.springframework.stereotype.Service

@Service
interface RedisService {
    fun set(key: String?, value: String?)

    fun get(key: String?): String?
}
