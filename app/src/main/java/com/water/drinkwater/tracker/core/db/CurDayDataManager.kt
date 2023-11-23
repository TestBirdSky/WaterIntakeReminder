package com.water.drinkwater.tracker.core.db

import androidx.lifecycle.MutableLiveData
import com.water.drinkwater.tracker.tool.WaterTools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Date：2023/11/14
 * Describe:
 */
object CurDayDataManager {
    private val waterDao by lazy { WaterRoom.waterDao }
    private var curDayString = WaterTools.getToday()
    var curDayWaterFinishRate = MutableLiveData<Int>(0)
    var curDayTargetValue = MutableLiveData<Int>()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            createDay()
            waterDao.getCurDayWater(WaterTools.getToday())?.let {
                (it.curFinishTotal * 100 / it.dayTotalSize).apply {
                    curDayWaterFinishRate.value = this
                    it.finishRate = this
                }
                curDayTargetValue.value = it.dayTotalSize
            }
        }
    }

    fun checkAndRefreshCurDay() {
        if (checkIsCurDay().not()) {
            CoroutineScope(Dispatchers.Main).launch {
                createDay()
                waterDao.getCurDayWater(WaterTools.getToday())?.let {
                    (it.curFinishTotal * 100 / it.dayTotalSize).apply {
                        curDayWaterFinishRate.value = this
                        it.finishRate = this
                    }
                    curDayTargetValue.value = it.dayTotalSize
                }
            }
        }
    }

    fun getCurDayWaterEntity(): DayWaterEntity? {
        return waterDao.getCurDayWater(WaterTools.getToday())
    }

    fun removeDrinkBean(bean: DrinkBean, cruDayWaterEntity: DayWaterEntity) {
        cruDayWaterEntity.apply {
            drinkList.removeIf { it.time == bean.time }
            curFinishTotal -= bean.size
            (curFinishTotal * 100 / dayTotalSize).apply {
                curDayWaterFinishRate.value = this
                finishRate = this
            }
            waterDao.update(this)
        }
    }

    private suspend fun createDay(): Boolean {
        if (waterDao.getCurDayWater(WaterTools.getToday()) == null) {
            newDayWaterEntity()
            return true
        }
        return false
    }

    private suspend fun newDayWaterEntity() {
        withContext(Dispatchers.Main) {
            val entity = DayWaterEntity().apply {
                curDayId = curDayString
                dayTotalSize = SharePTools.curDayGoalWaterValue
            }
            if (waterDao.getCurDayWater(WaterTools.getToday()) == null) {
                waterDao.add(entity)
            }
        }
    }

    fun addDrink(ml: Int, showFinishTip: () -> Unit, showLimitTips: () -> Unit) {
        val cur = waterDao.getCurDayWater(curDayString)
        cur?.let {
            it.curFinishTotal += ml
            if (it.curFinishTotal > it.dayTotalSize) {
                if (it.finishRate == 100) {
                    showLimitTips.invoke()
                    return
                } else {
                    it.dayTotalSize = it.curFinishTotal
                    showFinishTip.invoke()
                }
            }
            it.drinkList.add(DrinkBean(ml, System.currentTimeMillis()))
            (it.curFinishTotal * 100 / it.dayTotalSize).apply {
                curDayWaterFinishRate.value = this
                it.finishRate = this
            }
            waterDao.update(it)
        }
    }

    fun modifyTodayGoalDrink(total: Int, success: () -> Unit, failed: () -> Unit) {
        val cur = waterDao.getCurDayWater(curDayString)
        cur?.let {
            if (total < it.curFinishTotal) {
                failed.invoke()
                return
            }
            it.dayTotalSize = total
            (it.curFinishTotal * 100 / it.dayTotalSize).apply {
                curDayWaterFinishRate.value = this
                it.finishRate = this
            }
            curDayTargetValue.value = it.dayTotalSize
            waterDao.update(it)
            success.invoke()
        }
    }

    private fun checkIsCurDay(): Boolean {
        val isCur = curDayString == WaterTools.getToday()
        if (isCur.not()) {
            curDayString = WaterTools.getToday()
        }
        return isCur
    }

}