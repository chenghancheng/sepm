package com.hirrao.sepm.service.impl

import com.hirrao.sepm.service.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisServiceImpl @Autowired private constructor(private val redisTemplate: RedisTemplate<String, String>) :
    RedisService {
    private val DEFAULT_TIMEOUT: Long = 20
    private val DEFAULT_TIMEUNIT = TimeUnit.MINUTES

    override fun set(key: String?, value: String?) {
        redisTemplate.opsForValue()[key!!, value!!, DEFAULT_TIMEOUT] = DEFAULT_TIMEUNIT
    }

    override fun get(key: String?): String? {
        return redisTemplate.opsForValue()[key!!]
    }
}
