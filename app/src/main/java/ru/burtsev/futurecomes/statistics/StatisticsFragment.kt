package ru.burtsev.futurecomes.statistics


import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.mikephil.charting.components.XAxis
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_statisticks.*
import ru.burtsev.futurecomes.app.ViewModelFactory
import ru.burtsev.futurecomes.databinding.FragmentStatisticksBinding
import java.util.*
import javax.inject.Inject

class StatisticsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StatisticsViewModel>

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(StatisticsViewModel::class.java)
        viewModel.openDateDialogCommand.observe(this, Observer {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(activity,
                    DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                        viewModel.setDate(it, year, month, day)
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        })

        viewModel.messageError.observe(this, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStatisticksBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initCombinedChart()

    }

    private fun initCombinedChart() {
        combinedChart.description = null
        combinedChart.legend.isEnabled = false

        combinedChart.setTouchEnabled(true)
        combinedChart.isDragEnabled = true
        combinedChart.setScaleEnabled(true)
        combinedChart.isScaleXEnabled = true
        combinedChart.isScaleYEnabled = true
        combinedChart.axisRight.isEnabled = false
        combinedChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        combinedChart.xAxis.labelRotationAngle = -45f
    }


}
