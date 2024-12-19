package com.hirrao.sepm.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal open class DrugServiceTest @Autowired constructor(
    private val drugService: DrugService, private val userService: UserService
) {

    @BeforeEach
    fun init() {
        userService.register(1, "test", "test", "12345678912")
    }

    @Test
    fun insertDrug() {
        drugService.insertDrug(1L, 1, "test", "test", "test", 1.0f, 1)
    }

    @Test
    fun pageDrugInfo() {
        drugService.getPageDrugInfo(10491, 1, 5)
    }

    @Test
    fun pageDrugInfoByDrugName() {
        drugService.getPageDrugInfoByDrugName(1, 1, 1, "test")
    }

    @Test
    fun pageDrugInfoTotal() {
    }

    @Test
    fun pageDrugInfoTotalByDrugName() {
        drugService.getPageDrugInfoTotalByDrugName(1, "test")
    }

    @Test
    fun pageDrugAlertInfo() {
    }

    @Test
    fun pageDrugAlertInfoByDrugName() {
    }

    @Test
    fun pageDrugAlertInfoTotal() {
    }

    @Test
    fun pageDrugAlertInfoTotalByDrugName() {
    }

    @Test
    fun updateDrugIsActiveById() {
    }

    @Test
    fun deleteDrugById() {
    }
}