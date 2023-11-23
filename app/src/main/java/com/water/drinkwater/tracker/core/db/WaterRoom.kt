package com.water.drinkwater.tracker.core.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.water.drinkwater.tracker.core.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Date：2023/11/13
 * Describe:
 */
@Database(entities = [DayWaterEntity::class], version = 1)
abstract  class WaterRoom : RoomDatabase() {
    companion object {
        private val instance by lazy {
            Room.databaseBuilder(App.mApp, WaterRoom::class.java, "water.db").apply {
                allowMainThreadQueries()
                enableMultiInstanceInvalidation()
                fallbackToDestructiveMigration()
                setQueryExecutor { CoroutineScope(Dispatchers.IO).launch { it.run() } }
            }.build()
        }
        val waterDao get() = instance.waterDaoImpl()
    }

    abstract fun waterDaoImpl(): WaterDaoImpl
}
