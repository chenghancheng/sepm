package com.hirrao.sepm.service

import com.hirrao.sepm.entity.BloodPressure
import com.hirrao.sepm.entity.BloodPressureTable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
interface BloodPressureService {
    fun getBloodPressureList(uid: Int, date: LocalDate?): List<BloodPressure?>?

    fun insertBloodPressure(
        bplId: Long,
        userId: Int,
        sbp: Float,
        dbp: Float,
        recordTime: String?,
        classification: String?,
        riskLevel: String?
    )

    fun getBloodPressureTable(date: LocalDate?, uid: Int): BloodPressureTable?

    fun getRiskLevel(uid: Int, date: LocalDate?): List<String?>?
}
