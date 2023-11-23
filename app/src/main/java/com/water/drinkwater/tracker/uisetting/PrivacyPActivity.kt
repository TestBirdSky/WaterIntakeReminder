package com.water.drinkwater.tracker.uisetting

import com.water.drinkwater.tracker.databinding.ActivityPrivacyPolicyBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/14
 * Describe:
 */
class PrivacyPActivity : BaseWaterActivity() {
    override val viewBinding by lazy { ActivityPrivacyPolicyBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        viewBinding.wv.loadUrl("")
    }

}