package com.water.drinkwater.tracker.uiwelcome

import android.content.Intent
import com.water.drinkwater.tracker.core.LifeCycleCallback
import com.water.drinkwater.tracker.home.MainActivity
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.ActivitySelectedWeightBinding
import com.water.drinkwater.tracker.tool.WaterTools
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/13
 * Describe:
 */
class Guide3Activity : BaseWaterActivity() {
    override val viewBinding by lazy { ActivitySelectedWeightBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.tvNext.setOnClickListener {
            SharePTools.isActionGuide = true
            SharePTools.weightValue = viewBinding.numberPicker.value
            SharePTools.weightUnit = viewBinding.pickUnit.value
            SharePTools.curDayGoalWaterValue = WaterTools.getCurDayTarget(
                SharePTools.weightValue, SharePTools.genderS, SharePTools.weightUnit
            )
            startActivity(Intent(this, MainActivity::class.java))
            LifeCycleCallback.finishCurAllActivity()
        }
        viewBinding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        viewBinding.pickUnit.apply {
            displayedValues = arrayOf("Kg", "Lbs")
        }
    }

}