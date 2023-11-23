package com.water.drinkwater.tracker.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.core.db.CurDayDataManager
import com.water.drinkwater.tracker.core.db.SharePTools
import com.water.drinkwater.tracker.databinding.DialogAddGoalBinding

/**
 * Date：2023/11/13
 * Describe:
 */
class SetGoalDialogFragment(val curTotal:Int) : DialogFragment() {
    private lateinit var binding: DialogAddGoalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogAddGoalBinding.inflate(layoutInflater)
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
                val v = etInput.text.toString().toInt()
                CurDayDataManager.modifyTodayGoalDrink(v, success = {
                    SharePTools.curDayGoalWaterValue = v
                    dismiss()
                }, failed = {
                    Toast.makeText(context,"The value cannot be less than the amount of water consumed.",Toast.LENGTH_LONG).show()
                })
            }
            etInput.setText(curTotal.toString())
        }
    }

}