package ru.burtsev.futurecomes.statistics

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.burtsev.futurecomes.Repository
import ru.burtsev.futurecomes.SingleLiveEvent
import ru.burtsev.futurecomes.data.SelectedDate
import ru.burtsev.futurecomes.data.TimeCharacteristics
import ru.burtsev.futurecomes.data.TimeInterval
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class StatisticsViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val simpleDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("dd.MM.yyyy")
    }

    val openDateDialogCommand = SingleLiveEvent<SelectedDate>()
    val dateFrom = ObservableField<String>()
    val dateTo = ObservableField<String>()
    val min = ObservableField<String>("-")
    val max = ObservableField<String>("-")
    val average = ObservableField<String>("-")
    val median = ObservableField<String>("-")
    val interquartileRange = ObservableField<String>("-")
    val timeInterval = ObservableField<TimeInterval>()


    fun loadData() {
        if (dateFrom.get() != null && dateTo.get() != null) {
            repository.loadTimeInterval(dateFrom.get()!!, dateTo.get()!!)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ timeInterval ->
                        this.timeInterval.set(timeInterval)
                        TimeCharacteristics.calculate(timeInterval)
                                .subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ timeCharacteristics ->
                                    min.set(timeCharacteristics.min.toString())
                                    max.set(timeCharacteristics.max.toString())
                                    average.set(String.format("%1$,.2f", timeCharacteristics.average))
                                    median.set(String.format("%1$,.2f", timeCharacteristics.average))
                                    interquartileRange.set(timeCharacteristics.interquartileRange.toString())
                                },
                                        { throwableCalcucalte ->
                                            min.set("-")
                                            max.set("-")
                                            average.set("-")
                                            median.set("-")
                                            interquartileRange.set("-")
                                        })
                    },
                            { throwableLoading -> })
        }

    }

    fun showDateToDialog() {
        openDateDialogCommand.postValue(SelectedDate.To)
    }

    fun showDateFromDialog() {
        openDateDialogCommand.postValue(SelectedDate.From)
    }

    fun setDate(selectedDate: SelectedDate?, year: Int, month: Int, day: Int) {
        selectedDate?.let {
            Calendar.getInstance().run {
                set(year, month, day)
                val format = simpleDateFormat.format(this.time)
                when (it) {
                    SelectedDate.From -> dateFrom.set(format)
                    SelectedDate.To -> dateTo.set(format)
                }
            }
        }

    }
}
