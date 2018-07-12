package ru.burtsev.futurecomes.statistics

import android.databinding.BindingAdapter
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ru.burtsev.futurecomes.R
import ru.burtsev.futurecomes.data.TimeInterval
import java.util.*


object GraphBindings {

    @BindingAdapter("app:graph")
    @JvmStatic
    fun setTree(combinedChart: CombinedChart, timeInterval: TimeInterval?) {
        combinedChart.clear()
        if (timeInterval == null || timeInterval.interval.isEmpty()) return
        combinedChart.xAxis.setValueFormatter { value, axis ->
            try {
                val toList = timeInterval.interval.keys.toList()
                return@setValueFormatter toList[Math.ceil(value.toDouble()).toInt()]
            } catch (e: Exception) {
                return@setValueFormatter ""
            }
        }


        val lineEntries = LinkedList<Entry>()
        for ((index, entry) in timeInterval.interval.entries.withIndex()) {
            lineEntries.add(Entry(index.toFloat(), entry.value.toFloat()))
        }
        val lineSet = LineDataSet(lineEntries, "Временные значения")
        lineSet.color = combinedChart.context.resources.getColor(R.color.material_deep_teal_200)
        lineSet.lineWidth = 2.0f
        lineSet.setDrawValues(false)
        lineSet.setDrawCircles(false)
        lineSet.axisDependency = YAxis.AxisDependency.LEFT
        lineSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineData = LineData()
        lineData.addDataSet(lineSet)

        if (combinedChart.data != null) {
            combinedChart.data.setData(lineData)
        } else {

            val combinedData = CombinedData()
            combinedData.setData(lineData)
            combinedChart.data = combinedData
        }
        combinedChart.invalidate()

    }
}