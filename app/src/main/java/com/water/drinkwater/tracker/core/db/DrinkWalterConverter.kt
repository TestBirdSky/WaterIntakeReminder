package com.water.drinkwater.tracker.core.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Arrays


/**
 * Date：2023/11/13
 * Describe:
 */
class DrinkWalterConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<DrinkBean> {
        val listType = object : TypeToken<ArrayList<DrinkBean>>() {}.type
        // 将字符串转换为实体类列表
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: ArrayList<DrinkBean>): String {
        // 将实体类列表转换为字符串
        return Gson().toJson(value)
    }
}