package com.water.drinkwater.tracker.tool

import android.os.Build
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.water.drinkwater.tracker.BuildConfig
import com.water.drinkwater.tracker.Constants
import com.water.drinkwater.tracker.core.App
import com.water.drinkwater.tracker.core.db.SharePTools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.Locale

/**
 * Date：2023/11/24
 * Describe:
 */
object NetWaterTools {
    private val country by lazy { Locale.getDefault().country }
    private val lang by lazy { Locale.getDefault().language }
    var pareto = ""
        private set

    val url =
        "${Constants.getWaterUrl()}?textron=${WaterCache.getWaterDistinctId()}&wildlife=${System.currentTimeMillis()}" +
                "&payload=${Build.MODEL}&termcap=${Constants.getWaterPN()}&rascal=${Build.VERSION.RELEASE}&avon=${Build.BRAND}" +
                "&libel=$country&wildlife=${System.currentTimeMillis()}&compel=${lang}_$country&gaff=cavemen&sine=${WaterCache.getWaterAndId()}&rep=${BuildConfig.VERSION_NAME}&pareto=$pareto"

    fun iwant(mApp: App) {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                AdvertisingIdClient.getAdvertisingIdInfo(mApp).apply {
                    pareto = id ?: ""
                }
            }
            request()
        }
    }

    private fun request() {
        if (SharePTools.waterCStr.isNotBlank()) return
        val requestOkHttp =
            Request.Builder().url(url).get().addHeader("syzygy", Build.MANUFACTURER).build()
        var job: Job? = null
        OkHttpClient().newCall(requestOkHttp).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (job?.isActive == true) {
                    job?.cancel()
                    request()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body?.string()
                Log.i("iwant", "post onResponse $res ---code${response.code}")
                res?.let {
                    SharePTools.waterCStr = res
                }
            }
        })
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(8000)
            request()
        }
    }

    private fun initGaid() {

    }
}