package com.water.drinkwater.tracker.core

import android.app.Application
import android.content.Context

/**
 * Date：2023/11/13
 * Describe:
 */
class App : Application() {
    companion object {
        lateinit var mApp: App
            private set
        val mySp by lazy { mApp.getSharedPreferences("Water", Context.MODE_PRIVATE) }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        registerActivityLifecycleCallbacks(LifeCycleCallback())
    }
}