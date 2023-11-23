package com.water.drinkwater.tracker.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.core.db.CurDayDataManager
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.ActivityMainBinding
import com.water.drinkwater.tracker.drinkhistory.HistoryActivity
import com.water.drinkwater.tracker.drinkhistory.TodayHydrationLogActivity
import com.water.drinkwater.tracker.uibase.BaseWaterActivity
import com.water.drinkwater.tracker.uisetting.ActivitySetting

class MainActivity : BaseWaterActivity() {
    private var waterPer = SharePTools.waterPerValue
    override val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        viewBinding.apply {
            ivSetting.setOnClickListener {
                startActivity(Intent(this@MainActivity, ActivitySetting::class.java))
            }
            iv2.setOnClickListener {
                startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
            }
            iv1.setOnClickListener {
                startActivity(Intent(this@MainActivity, TodayHydrationLogActivity::class.java))
            }
            btnAddDrink.setOnClickListener {
                CurDayDataManager.addDrink(waterPer, showFinishTip = {
                    showFinishTips()
                })
            }
            ivEdit.setOnClickListener {
                showModifyTotal()
            }
            ivBgEdit.setOnClickListener {
                SetAmountPerDialogFragment {
                    waterPer = it
                    btnAddDrink.text = getString(R.string.drink_ml, it)
                }.show(supportFragmentManager, "111")
            }
            btnAddDrink.text = getString(R.string.drink_ml, waterPer)
        }
        CurDayDataManager.curDayWaterFinishRate.observe(this) {
            viewBinding.tvProgress.text = "$it%"
            viewBinding.progressHorizontal.progress = it
        }
        CurDayDataManager.curDayTargetValue.observe(this) {
            viewBinding.tvTodayAll.text = "${it}ml"
        }
    }

    override fun onResume() {
        super.onResume()
        CurDayDataManager.checkAndRefreshCurDay()
    }

    private fun showFinishTips() {
        AlertDialog.Builder(this).setTitle("Tips")
            .setMessage("Congratulations on completing your water drinking plan today.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }.show()
    }

    private fun showOverLimitTips() {
        AlertDialog.Builder(this).setTitle("Tips")
            .setMessage("The current amount of water consumed has reached the total amount for today. If you need to add more, please first modify the total amount of water consumed for today. Would you like to go to modify it?")
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("Modify") { dialog, _ ->
                dialog.dismiss()
                showModifyTotal()
            }.show()
    }

    private fun showModifyTotal() {
        SetGoalDialogFragment(
            CurDayDataManager.curDayTargetValue.value ?: SharePTools.curDayGoalWaterValue
        ).show(supportFragmentManager, "000")

    }
}