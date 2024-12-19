package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.BloodPressure
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import java.time.LocalDate

@Mapper
interface BloodPressureMapper : BaseMapper<BloodPressure?> {
    @Select("SELECT DATE(recordTime) as recordTime, AVG(SBP) as SBP , AVG(DBP) as DBP FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE(?, '-6 days') AND DATE(?) GROUP BY DATE(recordTime) ORDER BY DATE(recordTime);")
    fun getBloodPressureList(uid: Int, date: LocalDate?): List<BloodPressure?>?

    @Insert("INSERT INTO bloodPressureLog(bplId, uid, SBP, DBP, recordTime, classification, riskLevel) VALUES(#{bplId},#{uid}, #{sbp}, #{dbp}, #{recordTime}, #{classification}, #{riskLevel})")
    fun insertBloodPressure(
        bplId: Long,
        uid: Int,
        sbp: Float,
        dbp: Float,
        recordTime: String?,
        classification: String?,
        riskLevel: String?
    )

    @Select("SELECT riskLevel FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE(?, '-6 days') AND DATE(?) ORDER BY riskLevel;")
    fun getRiskLevel(uid: Int, date: LocalDate?): List<String?>?
}
