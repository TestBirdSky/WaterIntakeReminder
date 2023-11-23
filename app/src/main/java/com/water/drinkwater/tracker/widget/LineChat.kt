package com.water.drinkwater.tracker.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.water.drinkwater.tracker.R
import com.water.drinkwater.tracker.drinkhistory.HisUiBean
import com.water.drinkwater.tracker.tool.WaterTools

/**
 * Date：2023/11/22
 * Describe:
 */
class LineChat constructor(context: Context, a: AttributeSet) : View(context, a) {
    private val list = ArrayList<HisUiBean>()
    private val startXChat = dpToPx(50).toFloat() //X 轴起点
    private val startY = dpToPx(24).toFloat()
    private val startTitleY = dpToPx(27).toFloat()
    private val startYValue = dpToPx(43).toFloat()
    private val yMarginBottom = dpToPx(18)
    private val chatStartX = startXChat + dpToPx(20)//柱状图起点
    private val chatWidget = dpToPx(10) //柱状图宽度
    private val chatMargin = chatWidget + dpToPx(10)//柱状图间隔
    private var title = "2023"
    private val yPaint = Paint().apply {
        color = 0xFFF4F8FB.toInt()
        style = Paint.Style.STROKE
        strokeWidth = dpToPx(2).toFloat()
    }

    private val xPaint = Paint().apply {
        isAntiAlias = true//设置抗锯齿
        color = 0xFFC5CED0.toInt()//设置画笔颜色
        isDither = true// 防止抖动
        style = Paint.Style.STROKE // 边框
        strokeWidth = dpToPx(1).toFloat()//宽度
        pathEffect = DashPathEffect(FloatArray(2).apply {
            set(0, dpToPx(5).toFloat())
            set(1, dpToPx(3).toFloat())
        }, 0f)

    }

    private val linePaint = Paint().apply {
        color = 0xFF3AD1FF.toInt()
        style = Paint.Style.STROKE
        strokeWidth = chatWidget.toFloat()
    }


    private val textPaint = Paint().apply {
        style = Paint.Style.FILL
        color = 0xFF34435A.toInt()
        textSize = resources.getDimensionPixelSize(R.dimen.text_12).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val yEndChat = (height - dpToPx(31)).toFloat()
        canvas?.apply {
            val tWeight = textPaint.measureText("100%") + dpToPx(5)
            val tWeight2 = textPaint.measureText("0%") + dpToPx(5)
            drawText("100%", startXChat - tWeight, startYValue, textPaint)
            drawText("0%", startXChat - tWeight2, height - dpToPx(24).toFloat(), textPaint)
            drawLine(
                startXChat, startY, startXChat, (height - yMarginBottom).toFloat(), yPaint
            ) //Y 线
            setLayerType(LAYER_TYPE_SOFTWARE, null)
            val yMaxLength = yEndChat - startYValue + dpToPx(6) //最大长度
            val xLineMargin = yMaxLength / 4
            for (i in 0 until 5) {//画虚线
                drawLine(
                    startXChat,
                    yEndChat - xLineMargin * i,
                    width.toFloat(),
                    yEndChat - xLineMargin * i,
                    xPaint
                )
            }
            for (i in 0 until list.size) {
                val bean = list[i]
                drawLine(
                    chatStartX + chatMargin * i,
                    (yEndChat - (yMaxLength * bean.rate * 0.01f)),
                    chatStartX + chatMargin * i,
                    yEndChat,
                    linePaint
                )
                val day = WaterTools.getDayStr(bean.systemTime)
                val textW = textPaint.measureText(day)
                drawText(
                    day,
                    chatStartX + chatMargin * i - textW / 2,
                    height - dpToPx(10).toFloat(),
                    textPaint
                )
            }
            drawText(title, (width - startXChat) / 2 + startXChat, startTitleY, textPaint)
        }
    }

    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return Math.round(dp * density)
    }

    fun updateDate(data: ArrayList<HisUiBean>) {
        if (data.isEmpty()) return
        list.clear()
        list.addAll(data)
        title = WaterTools.getTimeYear(data[0].systemTime)
        invalidate()
    }



}