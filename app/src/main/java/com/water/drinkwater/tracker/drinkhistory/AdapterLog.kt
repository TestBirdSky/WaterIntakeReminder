package com.water.drinkwater.tracker.drinkhistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.water.drinkwater.tracker.core.db.CurDayDataManager
import com.water.drinkwater.tracker.core.db.DrinkBean
import com.water.drinkwater.tracker.databinding.ItemHistoryLogBinding
import com.water.drinkwater.tracker.tool.WaterTools

/**
 * Date：2023/11/22
 * Describe:
 */
class AdapterLog : RecyclerView.Adapter<AdapterLog.ViewH>() {
    private val cruDayWaterEntity by lazy {
        CurDayDataManager.getCurDayWaterEntity()
    }
    private val list = arrayListOf<DrinkBean>().apply {
        cruDayWaterEntity?.let {
            addAll(it.drinkList)
        }
    }

    inner class ViewH(val view: ItemHistoryLogBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        return ViewH(
            ItemHistoryLogBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewH, position: Int) {
        val d = list[position]
        holder.view.apply {
            tvSize.text = "${d.size}ml"
            tvTime.text = WaterTools.getTime(d.time)
            ivDelete.setOnClickListener {
                delListener.invoke(d)
            }
        }
    }

    var delListener: (d: DrinkBean) -> Unit = {}


    @SuppressLint("NotifyDataSetChanged")
    fun del(bean: DrinkBean) {
        list.remove(bean)
        CurDayDataManager.removeDrinkBean(bean, cruDayWaterEntity!!)
        notifyDataSetChanged()
    }
}