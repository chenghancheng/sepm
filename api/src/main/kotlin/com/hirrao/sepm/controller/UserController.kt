package com.hirrao.sepm.controller

import com.hirrao.sepm.entity.Result
import com.hirrao.sepm.service.UserService
import com.hirrao.sepm.utils.Result.success
import com.hirrao.sepm.utils.ThreadLocalUtil.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/userInfo")
    fun userInfo(): Result {
        val user = get()
        logger.debug("获取用户信息{}", user)
        return success(user)
    }

    @PutMapping("/update")
    fun update(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("更新用户信息{}", map)
        val user = get()
        val uid = user.uid
        val phoneNumber = map["phoneNumber"]
        val sex = map["sex"]
        val birthday = map["birthday"]
        userService.update(uid, phoneNumber, sex, birthday)
        return success()
    }

    @PostMapping("/accountsInfo")
    fun accountsInfo(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        return success(userService.accountsInfo(curPage, size))
    }

    @PostMapping("/modifyUserInfo")
    fun modifyUserInfo(@RequestBody map: Map<String?, String>): Result {
//        User user = ThreadLocalUtil.get();
//        var uid = user.uid;
        val uid = map["uid"]!!.toLong()
        val userName = map["userName"]
        val phoneNumber = map["phoneNumber"]
        val sex = map["sex"]
        val birthday = map["birthday"]
        val permission = map["permission"]!!.toInt()
        println("modifyUserInfo uid:$uid")
        println("modifyUserInfo userName:$userName")
        println("modifyUserInfo sex:$sex")
        println("modifyUserInfo birthday:$birthday")
        println("modifyUserInfo permission:$permission")

        userService.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission)
        return success()
    }

    @PostMapping("/updateUserName")
    fun updateUserName(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val userName = map["userName"]
        userService.updateUserName(uid, userName)
        return success()
    }

    @PostMapping("/updateSex")
    fun updateSex(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val sex = map["sex"]
        userService.updateSex(uid, sex)
        return success()
    }

    @PostMapping("/updateBirthday")
    fun updateBirthday(@RequestBody map: Map<String?, String?>): Result {
        val user = get()
        val uid = user.uid
        val birthday = map["birthday"]
        userService.updateBirthday(uid, birthday)
        return success()
    }

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestBody map: Map<String?, String>): Result {
//        System.out.println(map.size());
//        User user = ThreadLocalUtil.get();
        val uid = map["uid"]!!.toLong()
        userService.deleteUser(uid)
        println("delete uid:$uid")
        return success()
    }

    @PostMapping("/searchUserByCondition")
    fun searchUserByCondition(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        val searchCondition = map["searchCondition"]
        val conditionValue = map["conditionValue"]
        println("curPage:$curPage")
        println("size:$size")
        println("searchCondition:$searchCondition")
        println("conditionValue:$conditionValue")
        return if (!searchCondition!!.isEmpty() && !conditionValue!!.isEmpty()) {
            success(
                userService.searchUserByCondition(
                    curPage, size, searchCondition, conditionValue
                )
            )
        } else {
            success(userService.accountsInfo(curPage, size))
        }
    }
}
