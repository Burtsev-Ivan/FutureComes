package ru.burtsev.futurecomes

import io.reactivex.Single
import ru.burtsev.futurecomes.data.TimeInterval
import java.text.SimpleDateFormat
import java.util.*


class TestRepositoryImpl : Repository {

    private val formatter = SimpleDateFormat(("dd.MM.yyyy"))


    override fun loadTimeInterval(dateFrom: String, dateTo: String): Single<TimeInterval> {
        return Single.fromCallable {createTimeInterval(dateFrom, dateTo)}
    }


    private fun createTimeInterval(dateFrom: String, dateTo: String): TimeInterval {
        val mapDateValue = LinkedHashMap<String, Int>()
        val random = Random()

        val start = Calendar.getInstance()
        start.time = formatter.parse(dateFrom)
        val end = Calendar.getInstance()
        end.time = formatter.parse(dateTo)
        while (start.before(end)) {
            mapDateValue[formatter.format(start.time)] = random.nextInt(255)
            start.add(Calendar.DATE, 1)
        }

        return TimeInterval(mapDateValue)
    }

}