package com.hirrao.sepm.service

import com.hirrao.sepm.entity.DrugManageResult
import org.springframework.stereotype.Service

@Service
interface DrugService {
    fun insertDrug(
        drugId: Long, uid: Int, drugName: String?, frequency: String?, unit: String?, dosage: Float?, isActive: Int
    )

    //    List<DrugManageResult> getAllDrugInfo(long uid);
    fun getPageDrugInfo(uid: Int, curPage: Int, size: Int): List<DrugManageResult?>?

    fun getPageDrugInfoByDrugName(uid: Int, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    fun getPageDrugInfoTotal(uid: Int): Int

    fun getPageDrugInfoTotalByDrugName(uid: Int, drugName: String?): Int

    fun getPageDrugAlertInfo(uid: Int, curPage: Int, size: Int): List<DrugManageResult?>?

    fun getPageDrugAlertInfoByDrugName(uid: Int, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    fun getPageDrugAlertInfoTotal(uid: Int): Int

    fun getPageDrugAlertInfoTotalByDrugName(uid: Int, drugName: String?): Int

    fun updateDrugIsActiveById(drugId: Long, uid: Int, isActive: Int)

    fun deleteDrugById(uid: Int, drugId: Long)
}
