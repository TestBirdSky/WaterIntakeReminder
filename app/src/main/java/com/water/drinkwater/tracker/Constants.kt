package com.water.drinkwater.tracker

/**
 * Date：2023/11/24
 * Describe:
 */
object Constants {
    fun getWaterUrl(): String {
        return "${getHttps()}crypto.waterintake.link/collate/shuddery"
    }

    private fun getHttps(): String {
        return "https://"
    }

    fun getWaterPN(): String {
        return "com.water.intake.daily.reminder"
    }
}