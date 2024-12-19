package com.hirrao.javaee.dto

data class RegisterDto(
    val userName: String, val userPassword: String, val phoneNumber: String
)

data class LoginDto(
    val userName: String, val userPassword: String
)

data class ResetPasswordDto(
    val phoneNumber: String, val newPassword: String,val messageCode: Int
)