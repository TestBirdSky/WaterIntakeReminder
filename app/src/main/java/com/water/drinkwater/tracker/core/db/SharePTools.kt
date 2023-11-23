package com.water.drinkwater.tracker.core.db

import android.content.Context
import com.water.drinkwater.tracker.core.App

/**
 * Date：2023/11/13
 * Describe:
 */
object SharePTools {
    private val sp = App.mySp
    private val mEdit by lazy { sp.edit() }

    var isActionGuide = false
        get() {
            field = sp.getBoolean("isActionGuide", false)
            return field
        }
        set(value) {
            field = value
            mEdit.putBoolean("isActionGuide", field)
            mEdit.commit()
        }

    //性别2 男 1、女
    var genderS = 1
        get() {
            field = sp.getInt("genderS", 1)
            return field
        }
        set(value) {
            field = value
            mEdit.putInt("genderS", field)
            mEdit.commit()
        }


    var weightValue = 60
        get() {
            field = sp.getInt("weightValue", 60)
            return field
        }
        set(value) {
            field = value
            mEdit.putInt("weightValue", field)
            mEdit.commit()
        }


    var curDayGoalWaterValue = 2000
        get() {
            field = sp.getInt("curDayGoalWaterValue", 60)
            return field
        }
        set(value) {
            field = value
            mEdit.putInt("curDayGoalWaterValue", field)
            mEdit.commit()
        }

    var waterPerValue = 200
        get() {
            field = sp.getInt("waterPerValue", 200)
            return field
        }
        set(value) {
            field = value
            mEdit.putInt("waterPerValue", field)
            mEdit.commit()
        }

    //体重单位 默认1 kg 2 Lbs
    var weightUnit = 1
        get() {
            field = sp.getInt("weightUnit", 1)
            return field
        }
        set(value) {
            field = value
            mEdit.putInt("weightUnit", field)
            mEdit.commit()
        }

}