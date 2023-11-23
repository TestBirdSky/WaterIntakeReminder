package com.water.drinkwater.tracker.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.DialogAddGoalBinding
import com.water.drinkwater.tracker.databinding.DialogAmountPerBinding

/**
 * Date：2023/11/13
 * Describe:
 */
class SetAmountPerDialogFragment(val positiveClick: (value: Int) -> Unit) : DialogFragment() {
    private lateinit var binding: DialogAmountPerBinding
    private var curValue = SharePTools.waterPerValue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogAmountPerBinding.inflate(layoutInflater)
        dialog?.apply {
            window?.setBackgroundDrawableResource(R.drawable.bg_tras)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCur.text = curValue.toString()
            tvCancel.setOnClickListener { dismiss() }
            tvConfirm.setOnClickListener {
                SharePTools.waterPerValue = curValue
                positiveClick.invoke(curValue)
                dismiss()
            }
            tv200.setOnClickListener {
                curValue = 200
                refreshUI()
            }
            tv250.setOnClickListener {
                curValue = 250
                refreshUI()
            }
            tv300.setOnClickListener {
                curValue = 300
                refreshUI()
            }
            tv350.setOnClickListener {
                curValue = 350
                refreshUI()
            }
            tv400.setOnClickListener {
                curValue = 400
                refreshUI()
            }
            tv450.setOnClickListener {
                curValue = 450
                refreshUI()
            }
            refreshUI()
        }
    }

    private fun DialogAmountPerBinding.refreshUI() {
        tvCur.text = curValue.toString()
        context?.let {
            tv200.setBackgroundResource(R.drawable.bg_unselected2)
            tv300.setBackgroundResource(R.drawable.bg_unselected2)
            tv400.setBackgroundResource(R.drawable.bg_unselected2)
            tv350.setBackgroundResource(R.drawable.bg_unselected2)
            tv250.setBackgroundResource(R.drawable.bg_unselected2)
            tv450.setBackgroundResource(R.drawable.bg_unselected2)
            tv200.setTextColor(it.getColor(R.color.color_b2c1ca))
            tv300.setTextColor(it.getColor(R.color.color_b2c1ca))
            tv400.setTextColor(it.getColor(R.color.color_b2c1ca))
            tv350.setTextColor(it.getColor(R.color.color_b2c1ca))
            tv250.setTextColor(it.getColor(R.color.color_b2c1ca))
            tv450.setTextColor(it.getColor(R.color.color_b2c1ca))
            when (curValue) {
                200 -> {
                    tv200.setBackgroundResource(R.drawable.bg_selected2)
                    tv200.setTextColor(it.getColor(R.color.white))
                }
                300 -> {
                    tv300.setBackgroundResource(R.drawable.bg_selected2)
                    tv300.setTextColor(it.getColor(R.color.white))
                }
                250 -> {
                    tv250.setBackgroundResource(R.drawable.bg_selected2)
                    tv250.setTextColor(it.getColor(R.color.white))
                }
                350 -> {
                    tv350.setBackgroundResource(R.drawable.bg_selected2)
                    tv350.setTextColor(it.getColor(R.color.white))
                }
                400 -> {
                    tv400.setBackgroundResource(R.drawable.bg_selected2)
                    tv400.setTextColor(it.getColor(R.color.white))
                }

                450 -> {
                    tv450.setBackgroundResource(R.drawable.bg_selected2)
                    tv450.setTextColor(it.getColor(R.color.white))
                }
            }
        }


    }
}