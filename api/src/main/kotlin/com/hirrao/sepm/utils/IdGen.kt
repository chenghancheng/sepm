package com.hirrao.sepm.utils

import java.time.LocalDateTime
import kotlin.math.pow

class IdGen(private val length: Int) {
    private val dateTime = LocalDateTime.now()

    init {
        require(length > 7) {
            "length must be greater than 7"
        }
    }

    fun gen(): Int {
        val year = dateTime.year - 2024
        val day = dateTime.dayOfYear
        if (length == 8) return "${(1..9).random()}${year % 10}${(day % 100) / 10}${(0..9).random()}${day % 10}${(0..9).random()}${year / 10}${day / 100}".toInt()
        else {
            val random = (0..<2.0.pow(length - 7).toInt()).random()
            return "${year % 10}${(day % 100) / 10}${(0..9).random()}${day % 10}${(0..9).random()}${year / 10}${(0..9).random()}${day / 100}${random}".toInt()
        }
    }
}