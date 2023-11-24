package com.water.drinkwater.tracker.tool

import android.util.Log
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Date：2023/11/13
 * Describe:
 */
object WaterTools {

    fun getToday(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault()
        ).format(Date(System.currentTimeMillis()))
    }

    fun getTime(time: Long): String {
        return SimpleDateFormat(
            "aa hh:mm", Locale.getDefault()
        ).format(Date(time))
    }

    fun getTimeMon(time: Long): String {
        return SimpleDateFormat(
            "dd MMM", Locale.getDefault()
        ).format(Date(time))
    }

    fun getTimeYear(time: Long): String {
        return SimpleDateFormat(
            "yyyy", Locale.getDefault()
        ).format(Date(time))
    }

    fun getDayStr(time: Long): String {
        return SimpleDateFormat(
            "dd", Locale.getDefault()
        ).format(Date(time))
    }

    fun getCurDayTarget(weight: Int, sex: Int, unit: Int): Int {
        var target = weight
        if (unit == 2) {
            target = (weight * 0.453).toInt()
        }
        target *= 35
        if (sex == 1) {
            target = (target * 0.9).toInt()
        }
        return target
    }

}

fun String.messageDigest(): String {
    val hash = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    val hex = StringBuilder(hash.size * 2)
    for (b in hash) {
        var str = Integer.toHexString(b.toInt())
        if (b < 0x10) {
            str = "0$str"
        }
        hex.append(str.substring(str.length - 2))
    }
    return hex.toString()
}