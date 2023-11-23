package com.water.drinkwater.tracker.drinkhistory

import android.app.AlertDialog
import android.view.View
import com.water.drinkwater.tracker.databinding.ActivityTodaysLogBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/22
 * Describe:
 */
class TodayHydrationLogActivity : BaseWaterActivity() {
    private val mAdapterLog = AdapterLog()
    override val viewBinding by lazy { ActivityTodaysLogBinding.inflate(layoutInflater) }

    override fun initView() {
        viewBinding.apply {
            ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            rv.adapter = mAdapterLog
            if (mAdapterLog.itemCount == 0) {
                rv.visibility = View.GONE
                nullLayout.visibility = View.VISIBLE
            } else {
                rv.visibility = View.VISIBLE
                nullLayout.visibility = View.GONE
            }
            mAdapterLog.delListener = {
                delDialog {
                    mAdapterLog.del(it)
                }
            }
        }
    }

    private fun delDialog(del: () -> Unit) {
        AlertDialog.Builder(this).setTitle("Delete Tips")
            .setMessage("Are your sure delete this record?")
            .setPositiveButton("Delete") { dialog, _ ->
                dialog.dismiss()
                del.invoke()
            }.setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}