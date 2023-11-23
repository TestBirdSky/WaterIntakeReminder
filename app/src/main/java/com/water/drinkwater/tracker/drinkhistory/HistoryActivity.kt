package com.water.drinkwater.tracker.drinkhistory

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.water.drinkwater.tracker.databinding.ActivityHistoryBinding
import com.water.drinkwater.tracker.uibase.BaseWaterActivity

/**
 * Date：2023/11/22
 * Describe:
 */
class HistoryActivity : BaseWaterActivity() {
    private val mAdapter = AdapterHistory()
    private val mViewModel by lazy { ViewModelProvider(this)[HistoryViewModel::class.java] }
    override val viewBinding by lazy { ActivityHistoryBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        mViewModel.getData {
            viewBinding.apply {
                tvIntakeValue.text = "${mViewModel.waterIntakeAvgDay}ml"
                tvIntakeTimes.text = "${mViewModel.frequencyTimesAvg} Times"
                tvCRate.text = "${mViewModel.totalCompletionRateAvg}%"
                mAdapter.updateList(mViewModel.listUiHistoryBean)
                line.updateDate(mViewModel.listUiHistoryBean)
                if (mViewModel.endTitleStr.isEmpty()) {
                    viewTitle.visibility = View.GONE
                    tvTitleEnd.visibility = View.GONE
                }
                tvTitleStart.text = mViewModel.startTitleStr
                tvTitleEnd.text = mViewModel.endTitleStr
            }
        }
        viewBinding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        viewBinding.rv.adapter = mAdapter
        viewBinding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

    }
}