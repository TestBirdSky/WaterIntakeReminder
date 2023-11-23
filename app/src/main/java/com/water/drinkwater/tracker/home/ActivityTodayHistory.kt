package com.water.drinkwater.tracker.home

import com.water.drinkwater.tracker.databinding.ActivityTodaysLogBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/13
 * Describe:
 */
class ActivityTodayHistory : BaseWaterActivity() {
    override val viewBinding by lazy { ActivityTodaysLogBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}