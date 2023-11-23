package com.water.drinkwater.tracker.uisetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.core.db.CurDayDataManager
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.DialogAddGoalBinding
import com.water.drinkwater.tracker.databinding.DialogSelectedGenderWeightBinding
import com.water.drinkwater.tracker.tool.WaterTools

/**
 * Date：2023/11/22
 * Describe:
 */
class DialogSetGenderOrWeight : DialogFragment() {
    private var isChange = false
    private lateinit var binding: DialogSelectedGenderWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectedGenderWeightBinding.inflate(layoutInflater)
        dialog?.apply {
            window?.setBackgroundDrawableResource(R.drawable.bg_tras)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvCancel.setOnClickListener { dismiss() }
            tvConfirm.setOnClickListener {
                if (SharePTools.weightUnit != pickerUnit.value) {
                    SharePTools.weightUnit = pickerUnit.value
                    isChange = true
                }
                if (SharePTools.weightValue != pickerWeight.value) {
                    SharePTools.weightValue = pickerWeight.value
                    isChange = true
                }
                if (SharePTools.genderS != pickerSex.value) {
                    SharePTools.genderS = pickerSex.value
                    isChange = true
                }
                if (isChange) {
                    SharePTools.curDayGoalWaterValue = WaterTools.getCurDayTarget(
                        SharePTools.weightValue, SharePTools.genderS, SharePTools.weightUnit
                    )
                }
                dismiss()
            }
            pickerUnit.apply {
                value = SharePTools.weightUnit
                displayedValues = arrayOf("Kg", "Lbs")
            }
            pickerWeight.apply {
                value = SharePTools.weightValue
            }
            pickerSex.apply {
                value = SharePTools.genderS
                displayedValues = arrayOf("Female", "Male")
            }
        }
    }
}