package com.water.drinkwater.tracker.core

import android.content.Context

/**
 * Date：2023/11/24
 * Describe:
 */

class SpImpl(val mApp: App) {
    val sp = mApp.getSharedPreferences("Water Intake ", Context.MODE_PRIVATE)
    val edit = sp.edit()
}