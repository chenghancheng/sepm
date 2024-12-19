package com.hirrao.sepm.service.impl

import com.hirrao.sepm.entity.User
import com.hirrao.sepm.mapper.UserMapper
import com.hirrao.sepm.service.UserService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(private val userMapper: UserMapper) : UserService {
    override fun findByUsername(userName: String?): User? {
        return userMapper.findByUsername(userName)
    }

    override fun findByUid(uid: Int): User? {
        return userMapper.findByUid(uid)
    }

    override fun findByPhoneNumber(phoneNumber: String?): User? {
        return userMapper.findByPhoneNumber(phoneNumber)
    }

    override fun register(uid: Int, userName: String?, userPassword: String?, phoneNumber: String?) {
        userMapper.addUser(uid, userName, userPassword, phoneNumber)
    }

    override fun update(uid: Int, phoneNumber: String?, sex: String?, birthday: String?) {
        userMapper.update(uid, phoneNumber, sex, birthday)
    }

    override fun updatePassword(uid: Int, userPassword: String?) {
        userMapper.updatePassword(uid, userPassword)
    }

    override fun accountsInfo(curPage: Int, size: Int): IPage<User?>? {
        val page = Page<User>(curPage.toLong(), size.toLong())
        return userMapper.selectPage(page, null)
    }

    override fun modifyUserInfo(
        uid: Long, userName: String?, phoneNumber: String?, sex: String?, birthday: String?, permission: Int
    ) {
        userMapper.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission)
    }

    override fun deleteUser(uid: Long) {
        userMapper.deleteUser(uid)
    }

    override fun searchUserByCondition(
        curPage: Int, size: Int, searchCondition: String?, conditionValue: String?
    ): IPage<User?>? {
        val page = Page<User>(curPage.toLong(), size.toLong())
        val queryWrapper = QueryWrapper<User>()
        queryWrapper.eq(searchCondition, conditionValue)
        return userMapper.selectPage(page, queryWrapper)
    }

    override fun updateUserName(uid: Int, userName: String?) {
        userMapper.updateUserName(uid, userName)
    }

    override fun updateBirthday(uid: Int, birthday: String?) {
        userMapper.updateBirthday(uid, birthday)
    }

    override fun updateSex(uid: Int, sex: String?) {
        userMapper.updateSex(uid, sex)
    }
}
