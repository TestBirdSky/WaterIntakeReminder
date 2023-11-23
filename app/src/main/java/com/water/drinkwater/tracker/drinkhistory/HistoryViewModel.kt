package com.water.drinkwater.tracker.drinkhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.water.drinkwater.tracker.core.db.WaterRoom
import com.water.drinkwater.tracker.tool.WaterTools
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Date：2023/11/23
 * Describe:
 */
class HistoryViewModel : ViewModel() {
    private val day = 1000 * 60 * 60 * 24
    private val curDayTime = System.currentTimeMillis() - day * 7
    private val mDao by lazy { WaterRoom.waterDao }
    private var totalWaterAvg = 0
    private var totalWaterTimes = 0
    private var totalCompletionRate = 0
    var startTitleStr = ""
    var endTitleStr = ""
    var frequencyTimesAvg = 0 //平均每天的次数
        private set
    var waterIntakeAvgDay = 0 //平均每天的喝水
        private set
    var totalCompletionRateAvg = 0 //平均每天的完成率
        private set
    val listUiHistoryBean = arrayListOf<HisUiBean>()
    fun getData(success: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var waterDayNum = 0
            var endTime = 0L
            var startTime = 0L
            mDao.get7DayData(curDayTime).forEach {
                waterDayNum += 1
                var drinkTimes = it.drinkList.size
                totalWaterTimes += drinkTimes
                if (drinkTimes == 0) drinkTimes = 1
                totalWaterAvg += it.curFinishTotal
                totalCompletionRate += it.finishRate
                listUiHistoryBean.add(
                    HisUiBean(
                        it.timeBuild, it.finishRate, drinkTimes, it.curFinishTotal
                    )
                )
                if (waterDayNum == 1) {
                    startTime = it.timeBuild
                } else {
                    endTime = it.timeBuild
                }
            }
            if (waterDayNum > 0) {
                frequencyTimesAvg = totalWaterTimes / waterDayNum
                totalCompletionRateAvg = totalCompletionRate / waterDayNum
                waterIntakeAvgDay = totalWaterAvg / waterDayNum
            }
            startTitleStr = WaterTools.getTimeMon(startTime)
            if (endTime != 0L) {
                endTitleStr = WaterTools.getTimeMon(startTime)
                startTitleStr = WaterTools.getTimeMon(endTime)
            } else {
                startTitleStr = WaterTools.getTimeMon(startTime)
            }
            withContext(Dispatchers.Main) {
                success.invoke()
            }
        }
    }

}