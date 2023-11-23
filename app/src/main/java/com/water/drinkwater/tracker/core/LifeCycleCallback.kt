package com.water.drinkwater.tracker.core

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

/**
 * Date：2023/11/13
 * Describe:
 */
class LifeCycleCallback : ActivityLifecycleCallbacks {
    companion object {
        private val activityList: ArrayList<Activity> = arrayListOf()
        fun finishCurAllActivity() {
            ArrayList(activityList).forEach {
                it.finish()
            }
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityList.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        activityList.remove(activity)
    }
}