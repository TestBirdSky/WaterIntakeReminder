package com.water.drinkwater.tracker.drinkhistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.water.drinkwater.tracker.databinding.ItemHistoryBinding
import com.water.drinkwater.tracker.tool.WaterTools

/**
 * Date：2023/11/23
 * Describe:
 */
class AdapterHistory : RecyclerView.Adapter<AdapterHistory.ViewH>() {
    val list = arrayListOf<HisUiBean>()

    inner class ViewH(val view: ItemHistoryBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        return ViewH(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewH, position: Int) {
        val d = list[position]
        holder.view.apply {
            tvRate.text = "${d.rate}%"
            tvDate.text = WaterTools.getTimeMon(d.systemTime)
        }
    }

    fun updateList(data: ArrayList<HisUiBean>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}