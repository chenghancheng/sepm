package com.hirrao.sepm.service.impl

import com.hirrao.sepm.entity.UserIntro
import com.hirrao.sepm.mapper.UserIntroMapper
import com.hirrao.sepm.service.UserIntroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserIntroServiceImpl @Autowired private constructor(private val userIntroMapper: UserIntroMapper) :
    UserIntroService {
    override fun findUserIntro(uid: Int): UserIntro? {
        return userIntroMapper.findUserIntro(uid)
    }

    override fun updateUserIntro(uid: Long, intro: String?) {
        userIntroMapper.updateUserIntro(uid, intro)
    }

    override fun setUserIntro(uid: Int, intro: String?) {
        userIntroMapper.setUserIntro(uid, intro)
    }
}
