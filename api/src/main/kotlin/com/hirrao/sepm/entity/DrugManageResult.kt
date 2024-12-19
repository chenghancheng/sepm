package com.hirrao.sepm.entity

import java.time.LocalDate
import java.time.LocalTime

data class DrugManageResult(
    val drugId: Long? = null,
    val drugName: String? = null,
    val frequency: String? = null,
    val unit: String? = null,
    val dosage: Float? = null,
    val isActive: Int = 0,
    val alertId: Long? = null,
    val alertTime: LocalTime? = null,
    val eatTime: LocalDate? = null,
)
