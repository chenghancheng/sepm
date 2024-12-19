package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.HeartRateService
import com.hirrao.sepm.utils.Result.error
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.SnowFlake
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/heartRate")
class HeartRateController(private val heartRateService: HeartRateService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val snowFlake = SnowFlake(1, 3)

    @GetMapping("/searchHRById")
    fun searchHRById(@RequestBody map: Map<String?, String>): Result {
        logger.debug("通过id查找心率信息{}", map)
        val userId = map["userId"]!!.toLong()
        val result = heartRateService.searchHeartRateById(userId)
        return success(result)
    }

    @PostMapping("/insertHR")
    fun insertHR(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("插入心率信息{}", map)
        val userId = map["userId"]
        val heartRate = map["heartRate"]
        val date = map["date"]
        if (userId.isNullOrBlank() || heartRate.isNullOrBlank() || date.isNullOrBlank()) {
            return error(101, "错误输入")
        }
        heartRateService.insertHeartRate(snowFlake.nextId(), userId.toLong(), heartRate.toFloat(), date)
        return success()
    }
}
