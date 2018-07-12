package ru.burtsev.futurecomes.data

import io.reactivex.Single

class TimeCharacteristics {
    var min: Int? = null
    var max: Int? = null
    var average: Double? = null
    var median: Double? = null
    var interquartileRange: Int? = null

    companion object {
        /**
         * Вычисляет все требуемые характеристики временного ряда за минимальное количество проходов
         */
        fun calculate(timeInterval: TimeInterval): Single<TimeCharacteristics> {
            return Single.fromCallable {
                val timeCharacteristics = TimeCharacteristics()
                val sorted = timeInterval.interval.values.sorted()
                timeCharacteristics.apply {
                    max = sorted.last()
                    min = sorted.first()
                    median = if (sorted.size % 2 == 0) {
                        (sorted[sorted.size / 2 - 1] + sorted[sorted.size / 2]) / 2.0
                    } else {
                        sorted[sorted.size / 2].toDouble()
                    }
                    interquartileRange = sorted[sorted.size * 3 / 4] - sorted[sorted.size / 4]
                    average = sorted.average()
                }

            }
        }
    }

}