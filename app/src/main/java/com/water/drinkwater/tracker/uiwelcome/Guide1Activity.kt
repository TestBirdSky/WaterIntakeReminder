package com.water.drinkwater.tracker.uiwelcome

import android.content.Intent
import com.water.drinkwater.tracker.databinding.ActivityTipsChoiceGenderBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/13
 * Describe:
 */
class Guide1Activity : BaseWaterActivity() {
    override val viewBinding by lazy { ActivityTipsChoiceGenderBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.ivContinue.setOnClickListener {
            startActivity(Intent(this, Guide2Activity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {

    }
}