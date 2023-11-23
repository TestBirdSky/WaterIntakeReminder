package com.water.drinkwater.tracker.uisetting

import android.content.Intent
import android.widget.Toast
import com.water.drinkwater.tracker.databinding.ActivitySettingBinding
import com.water.drinkwater.tracker.databinding.DialogSelectedGenderWeightBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/14
 * Describe:
 */
class ActivitySetting : BaseWaterActivity() {
    override val viewBinding by lazy { ActivitySettingBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        viewBinding.tvPri.setOnClickListener {
            startActivity(Intent(this, PrivacyPActivity::class.java))
        }
        viewBinding.tvReminders.setOnClickListener {
            Toast.makeText(this,"Coming soon",Toast.LENGTH_SHORT).show()
        }
        viewBinding.tvGw.setOnClickListener {
            DialogSetGenderOrWeight().show(supportFragmentManager,"hijk")
        }
    }
}