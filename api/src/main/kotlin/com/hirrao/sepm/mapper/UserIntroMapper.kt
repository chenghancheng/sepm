package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.UserIntro
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface UserIntroMapper : BaseMapper<UserIntro?> {
    @Select("select * from userIntro where uid = #{uid}")
    fun findUserIntro(uid: Int): UserIntro?

    @Update("update userIntro set intro=#{intro} where uid = #{uid}")
    fun updateUserIntro(uid: Long, intro: String?)

    @Insert("insert into userIntro(uid, intro) values(#{uid}, #{intro})")
    fun setUserIntro(uid: Int, intro: String?)
}
