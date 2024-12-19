package com.hirrao.sepm.service

import com.hirrao.sepm.entity.User
import com.baomidou.mybatisplus.core.metadata.IPage
import org.springframework.stereotype.Service


@Service
interface UserService {
    fun findByUsername(userName: String?): User?

    fun findByUid(uid: Int): User?

    fun findByPhoneNumber(phoneNumber: String?): User?

    fun register(uid: Int, userName: String?, userPassword: String?, phoneNumber: String?)

    fun update(uid: Int, phoneNumber: String?, sex: String?, birthday: String?)

    fun updatePassword(uid: Int, userPassword: String?)

    fun accountsInfo(curPage: Int, size: Int): IPage<User?>?

    fun modifyUserInfo(
        uid: Long,
        userName: String?,
        phoneNumber: String?,
        sex: String?,
        birthday: String?,
        permission: Int
    )

    fun updateUserName(uid: Int, userName: String?)
    fun updateSex(uid: Int, sex: String?)
    fun updateBirthday(uid: Int, birthday: String?)

    fun deleteUser(uid: Long)

    fun searchUserByCondition(curPage: Int, size: Int, searchCondition: String?, conditionValue: String?): IPage<User?>?
}
