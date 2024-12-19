package com.hirrao.sepm.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal open class UserServiceTest @Autowired constructor(private val userService: UserService) {

    @BeforeEach
    fun init() {
        userService.register(1, "test", "test", "13574851354")
    }

    @Test
    fun findByUsername() {
        assertEquals("test", userService.findByUsername("test")?.userName)
    }

    @Test
    fun findByUid() {
        assertEquals("test", userService.findByUid(1)?.userName)
    }

    @Test
    fun register() {
        userService.register(2, "test2", "test2", "13574851355")
        assertEquals("test2", userService.findByUid(2)?.userName)
    }

    @Test
    fun update() {
        userService.update(1, "13574851355", "男", "2000-01-01")
        assertEquals("男", userService.findByUid(1)?.sex)
    }

    @Test
    fun updatePassword() {
        userService.updatePassword(1, "test2")
        assertEquals("test2", userService.findByUid(1)?.userPassword)
    }

    @Test
    fun accountsInfo() {
    }

    @Test
    fun modifyUserInfo() {
    }

    @Test
    fun deleteUser() {
        userService.deleteUser(1)
        Assertions.assertNull(userService.findByUid(1))
    }

    @Test
    fun searchUserByCondition() {
    }

    @Test
    fun updateUserName() {
        userService.updateUserName(1, "test2")
        assertEquals("test2", userService.findByUid(1)?.userName)
    }

    @Test
    fun updateBirthday() {
        userService.updateBirthday(1, "2000-01-01")
        assertEquals("2000-01-01", userService.findByUid(1)?.birthday)
    }

    @Test
    fun updateSex() {
        userService.updateSex(1, "女")
        assertEquals("女", userService.findByUid(1)?.sex)
    }
}