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
                setFinishRate(it)
                curDayTargetValue.value = it.dayTotalSize
            }
        }
    }

    fun checkAndRefreshCurDay() {
        if (checkIsCurDay().not()) {
            CoroutineScope(Dispatchers.Main).launch {
                createDay()
                waterDao.getCurDayWater(WaterTools.getToday())?.let {
                    setFinishRate(it)
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
            setFinishRate(this)
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

    fun addDrink(ml: Int, showFinishTip: () -> Unit) {
        val cur = waterDao.getCurDayWater(curDayString)
        cur?.let {
            it.curFinishTotal += ml
            it.drinkList.add(DrinkBean(ml, System.currentTimeMillis()))
            setFinishRate(it)
            waterDao.update(it)
            if (it.finishRate == 100) {
                showFinishTip.invoke()
            }
        }
    }

    private fun setFinishRate(dayWaterEntity: DayWaterEntity) {
        (dayWaterEntity.curFinishTotal * 100 / dayWaterEntity.dayTotalSize).apply {
            var finrate = this
            if (finrate == 0 && dayWaterEntity.curFinishTotal > 0) {
                finrate = 1
            }
            if (finrate > 100) {
                finrate = 100
            }
            curDayWaterFinishRate.value = finrate
            dayWaterEntity.finishRate = finrate
        }
    }

    fun modifyTodayGoalDrink(total: Int) {
        val cur = waterDao.getCurDayWater(curDayString)
        cur?.let {
            it.dayTotalSize = total
            setFinishRate(it)
            curDayTargetValue.value = it.dayTotalSize
            waterDao.update(it)
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