package com.water.drinkwater.tracker.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Date：2023/11/13
 * Describe:
 */
@Entity
@TypeConverters(DrinkWalterConverter::class)
class DayWaterEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var curDayId = ""
    var timeBuild: Long = System.currentTimeMillis()//创建时间
    var dayTotalSize: Int = 2000 //当天总共的喝水量
    var curFinishTotal: Int = 0//这一天完成的总量
    var finishRate: Int = 0// 完成率
    var drinkList: ArrayList<DrinkBean> = arrayListOf()
}