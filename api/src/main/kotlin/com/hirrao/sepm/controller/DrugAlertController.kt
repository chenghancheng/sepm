package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.DrugAlertService
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.SnowFlake
import com.hirrao.sepm.utils.ThreadLocalUtil.get
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/drugAlert")
class DrugAlertController @Autowired internal constructor(private val drugAlertService: DrugAlertService) {
    private val snowFlake = SnowFlake(1, 5)

    @PostMapping("/insertDrug")
    fun insertDrug(@RequestBody map: Map<String?, String>): Result {
        val alertId = snowFlake.nextId()
        val user = get()
        val uid = user.uid
        val drugId = map["drugId"]!!.toLong()
        val alertTime = map["alertTime"]?.let { LocalTime.parse(it) }
        val eatTime = LocalDate.parse("2000-01-01")
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime)
        return success()
    }

    @PostMapping("/updateDrugAlertIsActiveById")
    fun updateDrugAlertIsActiveById(@RequestBody map: Map<String?, String>): Result {
        val alertId = map["alertId"]!!.toLong()
        val isActive = map["isActive"]!!.toInt()
        drugAlertService.updateDrugAlertIsActiveById(alertId, isActive)
        return success()
    }

    @PostMapping("/updateDrugAlertEatTimeById")
    fun updateDrugAlertEatTimeById(@RequestBody map: Map<String?, String>): Result {
        val alertId = map["alertId"]!!.toLong()
        val eatTime = map["eatTime"]?.let { LocalDate.parse(it) }
        drugAlertService.updateDrugAlertEatTimeById(alertId, eatTime)
        return success()
    }

    @PostMapping("/deleteDrugAlertById")
    fun deleteDrugAlertById(@RequestBody map: Map<String?, String>): Result {
        val alertId = map["alertId"]!!.toLong()
        val user = get()
        val uid = user.uid
        val drugId = map["drugId"]!!.toLong()
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId)
        return success()
    }
}
