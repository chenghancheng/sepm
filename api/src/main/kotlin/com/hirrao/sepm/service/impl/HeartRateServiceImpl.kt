package com.hirrao.sepm.service.impl

import com.hirrao.sepm.entity.HeartRate
import com.hirrao.sepm.mapper.HeartRateMapper
import com.hirrao.sepm.service.HeartRateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HeartRateServiceImpl @Autowired constructor(private val heartRateMapper: HeartRateMapper) : HeartRateService {
    override fun searchHeartRateById(uid: Long?): List<HeartRate?>? {
        return heartRateMapper.searchHeartRateById(uid)
    }

    override fun insertHeartRate(hrlId: Long, uid: Long, heartRate: Float, recordTime: String?) {
        heartRateMapper.insertHeartRate(hrlId, uid, heartRate, recordTime)
    }
}
