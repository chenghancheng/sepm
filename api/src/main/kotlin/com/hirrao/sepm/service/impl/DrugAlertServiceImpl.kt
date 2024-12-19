package com.hirrao.sepm.service.impl

import com.hirrao.sepm.mapper.DrugAlertMapper
import com.hirrao.sepm.service.DrugAlertService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class DrugAlertServiceImpl @Autowired constructor(private val drugAlertMapper: DrugAlertMapper) : DrugAlertService {
    override fun insertDrugAlert(alertId: Long, uid: Int, drugId: Long, alertTime: LocalTime?, eatTime: LocalDate?) {
        drugAlertMapper.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime)
    }

    override fun updateDrugAlertIsActiveById(alertId: Long, isActive: Int) {
        drugAlertMapper.updateDrugAlertIsActiveById(alertId, isActive)
    }

    override fun updateDrugAlertEatTimeById(alertId: Long, eatTime: LocalDate?) {
        drugAlertMapper.updateDrugAlertEatTimeById(alertId, eatTime)
    }

    override fun deleteDrugAlertById(alertId: Long, uid: Int, drugId: Long) {
        drugAlertMapper.deleteDrugAlertById(alertId, uid, drugId)
    }
}
