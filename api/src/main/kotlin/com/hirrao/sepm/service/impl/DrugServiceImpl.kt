package com.hirrao.sepm.service.impl

import com.hirrao.sepm.entity.DrugManageResult
import com.hirrao.sepm.mapper.DrugMapper
import com.hirrao.sepm.service.DrugService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DrugServiceImpl @Autowired constructor(private val drugMapper: DrugMapper) : DrugService {
    override fun insertDrug(
        drugId: Long, uid: Int, drugName: String?, frequency: String?, unit: String?, dosage: Float?, isActive: Int
    ) {
        drugMapper.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
    }

    //    @Override
    //    public List<DrugManageResult> getAllDrugInfo(long uid){
    //        return drugMapper.getAllDrugInfo(uid);
    //    }
    override fun getPageDrugInfo(uid: Int, curPage: Int, size: Int): List<DrugManageResult?>? {
        return drugMapper.getPageDrugInfo(uid, (curPage - 1) * size, size)
    }

    override fun getPageDrugInfoByDrugName(
        uid: Int, curPage: Int, size: Int, drugName: String?
    ): List<DrugManageResult?>? {
        return drugMapper.getPageDrugInfoByDrugName(uid, (curPage - 1) * size, size, drugName)
    }

    override fun getPageDrugInfoTotal(uid: Int): Int {
        return drugMapper.getPageDrugInfoTotal(uid)
    }

    override fun getPageDrugInfoTotalByDrugName(uid: Int, drugName: String?): Int {
        return drugMapper.getPageDrugInfoTotalByDrugName(uid, drugName)
    }

    override fun getPageDrugAlertInfo(
        uid: Int, curPage: Int, size: Int
    ): List<DrugManageResult?>? {
        return drugMapper.getPageDrugAlertInfo(uid, (curPage - 1) * size, size)
    }

    override fun getPageDrugAlertInfoByDrugName(
        uid: Int, curPage: Int, size: Int, drugName: String?
    ): List<DrugManageResult?>? {
        return drugMapper.getPageDrugAlertInfoByDrugName(uid, (curPage - 1) * size, size, drugName)
    }

    override fun getPageDrugAlertInfoTotal(uid: Int): Int {
        return drugMapper.getPageDrugAlertInfoTotal(uid)
    }

    override fun getPageDrugAlertInfoTotalByDrugName(uid: Int, drugName: String?): Int {
        return drugMapper.getPageDrugAlertInfoTotalByDrugName(uid, drugName)
    }

    override fun updateDrugIsActiveById(drugId: Long, uid: Int, isActive: Int) {
        drugMapper.updateDrugIsActiveById(drugId, uid, isActive)
    }

    override fun deleteDrugById(uid: Int, drugId: Long) {
        drugMapper.deleteDrugById(uid, drugId)
    }
}
