package com.hirrao.sepm.service

import com.hirrao.sepm.entity.HeartRate
import org.springframework.stereotype.Service

@Service
interface HeartRateService {
    fun searchHeartRateById(uid: Long?): List<HeartRate?>?
    fun insertHeartRate(hrlId: Long, uid: Long, heartRate: Float, recordTime: String?)
}
