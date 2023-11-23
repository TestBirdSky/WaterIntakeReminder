package com.water.drinkwater.tracker.uiwelcome

import android.content.Intent
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.ActivitySelectedGenderBinding
import com.water.drinkwater.tracker.databinding.ActivityTipsChoiceGenderBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/13
 * Describe:
 */
class Guide2Activity : BaseWaterActivity() {
    private var sex = 1

    override val viewBinding by lazy { ActivitySelectedGenderBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.tvNext.setOnClickListener {
            SharePTools.genderS = sex
            startActivity(Intent(this, Guide3Activity::class.java))
        }
        viewBinding.apply {
            genderIvMan.setOnClickListener {
                sex = 2
                genderIvMan.setBackgroundResource(R.drawable.bg_selected)
                genderIvWoman.setBackgroundResource(R.drawable.bg_unselected)
                tvNext.isEnabled = true
                viewBinding.tvNext.setBackgroundResource(R.drawable.bg_btn2)
            }
            genderIvWoman.setOnClickListener {
                sex = 1
                genderIvMan.setBackgroundResource(R.drawable.bg_unselected)
                genderIvWoman.setBackgroundResource(R.drawable.bg_selected)
                tvNext.isEnabled = true
                viewBinding.tvNext.setBackgroundResource(R.drawable.bg_btn2)
            }
        }
    }

    override fun onBackPressed() {

    }
}