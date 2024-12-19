package com.hirrao.sepm.utils

object BloodPressureUtil {

    fun generateClassification(sbp: Float, dbp: Float): String {
        return if (sbp >= 180 || dbp >= 110) {
            "3级高血压"
        } else if (sbp >= 160 || dbp >= 100) {
            "2级高血压"
        } else if (sbp >= 140 || dbp >= 90) {
            "1级高血压"
        } else {
            "正常"
        }
    }


    fun generateRiskLevel(sbp: Float, dbp: Float): String {
        return if (sbp >= 180 || dbp >= 110) {
            "重度"
        } else if (sbp >= 160 || dbp >= 100) {
            "中度"
        } else if (sbp >= 140 || dbp >= 90) {
            "轻度"
        } else if (sbp >= 120 || dbp >= 80) {
            "正常高值"
        } else if (sbp <= 90 || dbp <= 60) {
            "偏低"
        } else {
            "正常"
        }
    }
}
