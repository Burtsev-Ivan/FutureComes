package ru.burtsev.futurecomes.statistics.repository

import io.reactivex.Single
import ru.burtsev.futurecomes.data.TimeInterval

interface Repository {
    fun loadTimeInterval(dateFrom: String, dateTo: String): Single<TimeInterval>
}