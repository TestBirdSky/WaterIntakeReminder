package com.water.drinkwater.tracker.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Date：2023/11/13
 * Describe:
 */
@Dao
interface WaterDaoImpl {

    @Query("SELECT * FROM DayWaterEntity Where timeBuild >:curDay  order by timeBuild desc limit 7")
    fun get7DayData(curDay: Long): List<DayWaterEntity>

    @Query("SELECT * FROM DayWaterEntity order by timeBuild desc")
    fun getAllDay(): List<DayWaterEntity>

    @Query("SELECT * FROM DayWaterEntity Where curDayId=:curDay limit 1")
    fun getCurDayWater(curDay: String): DayWaterEntity?

    @Insert
    fun add(entity: DayWaterEntity)

    @Delete
    fun delete(entity: DayWaterEntity)

    @Update
    fun update(entity: DayWaterEntity)

}