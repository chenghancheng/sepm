package com.hirrao.sepm.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@Transactional
internal open class BloodPressureServiceTest @Autowired constructor(
    private val bloodPressureService: BloodPressureService, private val userService: UserService
) {

    @BeforeEach
    fun init() {
        userService.register(1, "test", "test", "13574851354")
    }


    @Test
    fun bloodPressureList() {
    }

    @Test
    fun insertBloodPressure() {
        bloodPressureService.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
    }

    @Test
    fun bloodPressureTable() {
        bloodPressureService.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
        bloodPressureService.insertBloodPressure(2, 1, 130f, 90f, "2021-01-02 12:00:00", "正常", "中风险")
        println(bloodPressureService.getBloodPressureTable(LocalDate.parse("2021-01-01"), 1))
    }


    @Test
    fun riskLevel() {
    }
}