package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.BloodPressureTable
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDate

interface BloodPressureTableMapper : BaseMapper<BloodPressureMapper?> {
    @Select("SELECT AVG(SBP) as avgSBP, MIN(SBP) as minSBP, MAX(SBP) as maxSBP, AVG(DBP) as avgDBP, MIN(DBP) as minDBP, MAX(DBP) as maxDBP, AVG(SBP-DBP) as avgPP, MIN(SBP-DBP) as minPP, MAX(SBP-DBP) as maxPP FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE(?, '-6 days') AND DATE(?);;")
    fun getBloodPressureTable(date: LocalDate?, uid: Int): BloodPressureTable?
}
