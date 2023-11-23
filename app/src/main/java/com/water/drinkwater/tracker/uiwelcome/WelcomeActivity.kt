package com.water.drinkwater.tracker.uiwelcome

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.water.drinkwater.tracker.home.MainActivity
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.ActivityWeclomeBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2023/11/13
 * Describe:
 */
class WelcomeActivity : BaseWaterActivity() {
    private val isJumpMain by lazy { SharePTools.isActionGuide }
    override val viewBinding: ActivityWeclomeBinding by lazy {
        ActivityWeclomeBinding.inflate(
            layoutInflater
        )
    }

    override fun initView() {
        viewBinding.ivIconApp.post {
            lifecycleScope.launch {
                while (viewBinding.linerPro.progress < 100) {
                    viewBinding.linerPro.progress += 1
                    delay(18)
                }
                if (isResume) {
                    if (isJumpMain) {
                        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this@WelcomeActivity, Guide1Activity::class.java))
                    }
                }
                finish()
            }
        }
    }

    override fun onBackPressed() {

    }
}