package com.hirrao.sepm.service

import com.hirrao.sepm.entity.UserIntro
import org.springframework.stereotype.Service

@Service
interface UserIntroService {
    fun findUserIntro(uid: Int): UserIntro?
    fun updateUserIntro(uid: Long, intro: String?)
    fun setUserIntro(uid: Int, intro: String?)
}
