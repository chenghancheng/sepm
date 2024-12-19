package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.DrugAlert
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Update
import java.time.LocalDate
import java.time.LocalTime

@Mapper
interface DrugAlertMapper : BaseMapper<DrugAlert?> {
    @Insert("INSERT INTO drugAlert VALUES (#{alertId},#{uid},#{drugId}, #{alertTime},#{eatTime})")
    fun insertDrugAlert(alertId: Long, uid: Int, drugId: Long, alertTime: LocalTime?, eatTime: LocalDate?)

    @Update("UPDATE drug SET isActive=#{isActive} WHERE alertId=#{alertId}")
    fun updateDrugAlertIsActiveById(alertId: Long, isActive: Int)

    @Update("UPDATE drugAlert SET eatTime=#{eatTime} WHERE alertId=#{alertId}")
    fun updateDrugAlertEatTimeById(alertId: Long, eatTime: LocalDate?)

    @Delete("DELETE FROM drugAlert WHERE alertId=#{alertId} AND uid=#{uid} AND drugId=#{drugId}")
    fun deleteDrugAlertById(alertId: Long, uid: Int, drugId: Long)
}
