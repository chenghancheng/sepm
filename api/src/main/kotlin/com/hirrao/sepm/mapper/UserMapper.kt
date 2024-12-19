package com.hirrao.sepm.mapper

import com.hirrao.sepm.entity.User
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper : BaseMapper<User?> {
    //根据用户名查询用户
    @Select("select * from user where userName = #{userName}")
    fun findByUsername(userName: String?): User?

    @Select("select * from user where uid = #{uid}")
    fun findByUid(uid: Int): User?

    @Select("select * from user where phoneNumber = #{phoneNumber}")
    fun findByPhoneNumber(phoneNumber: String?): User?

    @Insert("insert into user(uid,userName, userPassword,phoneNumber) values(#{uid},#{userName}, #{userPassword},#{phoneNumber})")
    fun addUser(uid: Int, userName: String?, userPassword: String?, phoneNumber: String?)

    @Update("update user set phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday} where uid=#{uid}")
    fun update(uid: Int, phoneNumber: String?, sex: String?, birthday: String?)

    @Update("update user set userPassword=#{userPassword} where uid = #{uid}")
    fun updatePassword(uid: Int, userPassword: String?)

    @Update("update user set uid=#{uid},userName=#{userName},phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday},permission=#{permission} where uid=#{uid}")
    fun modifyUserInfo(
        uid: Long,
        userName: String?,
        phoneNumber: String?,
        sex: String?,
        birthday: String?,
        permission: Int
    )

    @Delete("delete from user where uid=#{uid}")
    fun deleteUser(uid: Long)

    @Update("update user set userName=#{userName} where uid=#{uid}")
    fun updateUserName(uid: Int, userName: String?)

    @Update("update user set sex=#{sex} where uid=#{uid}")
    fun updateSex(uid: Int, sex: String?)

    @Update("update user set birthday=#{birthday} where uid=#{uid}")
    fun updateBirthday(uid: Int, birthday: String?)
}
