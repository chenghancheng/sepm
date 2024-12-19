package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.DrugAlertService
import com.hirrao.sepm.service.DrugService
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.SnowFlake
import com.hirrao.sepm.utils.ThreadLocalUtil.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/drug")
class DrugController @Autowired internal constructor(
    private val drugService: DrugService, private val drugAlertService: DrugAlertService
) {
    private val snowFlakeDrug = SnowFlake(1, 4)

    private val snowFlakeDrugAlert = SnowFlake(1, 5)

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/getPageDrugsInfo")
    fun getPageDrugsInfo(@RequestBody map: Map<String?, String>): Result {
        val user = get()
        val uid = user.uid
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        return success(drugService.getPageDrugInfo(uid, curPage, size))
    }

    @PostMapping("/getPageDrugsInfoByDrugName")
    fun getPageDrugsInfoByDrugName(@RequestBody map: Map<String?, String>): Result {
        val user = get()
        val uid = user.uid
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        val drugName = map["drugName"]
        if (drugName!!.isNotEmpty()) {
            println("notEmpty")
            return success(drugService.getPageDrugInfoByDrugName(uid, curPage, size, drugName))
        } else {
            println("Empty")
            return success(drugService.getPageDrugInfo(uid, curPage, size))
        }
    }

    @GetMapping("/getPageDrugInfoTotal")
    fun pageDrugInfoTotal(): Result {
        val user = get()
        val uid = user.uid
        return success(drugService.getPageDrugInfoTotal(uid))
    }

    @PostMapping("/getPageDrugInfoTotalByDrugName")
    fun getPageDrugInfoTotalByDrugName(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val drugName = map["drugName"]
        return if (drugName!!.isNotEmpty()) {
            success(drugService.getPageDrugInfoTotalByDrugName(uid, drugName))
        } else {
            success(drugService.getPageDrugInfoTotal(uid))
        }
    }

    @PostMapping("/getPageDrugsAlertInfo")
    fun getPageDrugsAlertInfo(@RequestBody map: Map<String?, String>): Result {
        val user = get()
        val uid = user.uid
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        return success(drugService.getPageDrugAlertInfo(uid, curPage, size))
    }

    @PostMapping("/getPageDrugsAlertInfoByDrugName")
    fun getPageDrugsAlertInfoByDrugName(@RequestBody map: Map<String?, String>): Result {
        val user = get()
        val uid = user.uid
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        val drugName = map["drugName"]
        if (drugName!!.isNotEmpty()) {
            println("notEmpty")
            return success(drugService.getPageDrugAlertInfoByDrugName(uid, curPage, size, drugName))
        } else {
            println("Empty")
            return success(drugService.getPageDrugAlertInfo(uid, curPage, size))
        }
    }

    @GetMapping("/getPageDrugAlertInfoTotal")
    fun pageDrugAlertInfoTotal(): Result {
        val user = get()
        val uid = user.uid
        return success(drugService.getPageDrugAlertInfoTotal(uid))
    }


    @PostMapping("/getPageDrugAlertInfoTotalByDrugName")
    fun getPageDrugAlertInfoTotalByDrugName(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val drugName = map["drugName"]
        return if (drugName!!.isNotEmpty()) {
            success(drugService.getPageDrugAlertInfoTotalByDrugName(uid, drugName))
        } else {
            success(drugService.getPageDrugAlertInfoTotal(uid))
        }
    }

    @PostMapping("/insertDrug")
    fun insertDrug(@RequestBody map: Map<String?, String>): Result {
        val drugId = snowFlakeDrug.nextId()
        val user = get()
        val uid = user.uid
        val drugName = map["drugName"]
        val frequency = map["frequency"]
        val unit = map["unit"]
        val dosage = map["dosage"]!!.toFloat()
        val isActive = map["isActive"]!!.toInt()
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
        return success()
    }

    @PostMapping("/insertDrugAndAlert")
    fun insertDrugAndAlert(@RequestBody map: Map<String?, String>): Result {
        val drugId = snowFlakeDrug.nextId()
        val user = get()
        val uid = user.uid
        val drugName = map["drugName"]
        val frequency = map["frequency"]
        val unit = map["unit"]
        val dosage = map["dosage"]!!.toFloat()
        val isActive = map["isActive"]!!.toInt()
        logger.debug("uid{}", uid)
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
        val alertId = snowFlakeDrugAlert.nextId()
        val alertTime = map["alertTime"]?.let { LocalTime.parse(it) }
        val eatTime = LocalDate.parse("2000-01-01")
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime)
        return success()
    }

    @PostMapping("/updateDrugIsActiveById")
    fun updateDrugIsActiveById(@RequestBody map: Map<String?, String>): Result {
        val drugId = map["drugId"]!!.toLong()
        val user = get()
        val uid = user.uid
        val isActive = map["isActive"]!!.toInt()
        drugService.updateDrugIsActiveById(drugId, uid, isActive)
        return success()
    }

    @PostMapping("/deleteDrugById")
    fun deleteDrugById(@RequestBody map: Map<String?, String>): Result {
        val user = get()
        val uid = user.uid
        val drugId = map["drugId"]!!.toLong()
        drugService.deleteDrugById(uid, drugId)
        return success()
    }

    @PostMapping("/deleteDrugAndDrugAlertById")
    fun deleteDrugAndDrugAlertById(@RequestBody map: Map<String?, String>): Result {
        val alertId = map["alertId"]!!.toLong()
        val user = get()
        val uid = user.uid
        val drugId = map["drugId"]!!.toLong()
        drugService.deleteDrugById(uid, drugId)
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId)
        return success()
    }
}
