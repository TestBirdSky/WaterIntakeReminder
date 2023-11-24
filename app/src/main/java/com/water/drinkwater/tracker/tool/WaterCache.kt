package com.water.drinkwater.tracker.tool

import android.provider.Settings
import com.water.drinkwater.tracker.core.App
import com.water.drinkwater.tracker.core.db.SharePTools

/**
 * Date：2023/11/24
 * Describe:
 */
object WaterCache {

    fun getWaterDistinctId(): String {
        return getWaterAndId().messageDigest()
    }

    fun getWaterAndId(): String {
        SharePTools.waterAndroidId.apply {
            if (this.isBlank()) {
                runCatching {
                    Settings.Secure.getString(
                        App.mApp.contentResolver, Settings.Secure.ANDROID_ID
                    ).apply {
                        SharePTools.waterAndroidId = this
                        return this
                    }
                }
            }
            return this
        }

    }

}