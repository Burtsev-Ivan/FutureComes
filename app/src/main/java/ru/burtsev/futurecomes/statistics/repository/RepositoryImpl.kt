package ru.burtsev.futurecomes.statistics.repository

import io.reactivex.Single
import ru.burtsev.futurecomes.data.TimeInterval
import ru.burtsev.futurecomes.network.ServiceApi

class RepositoryImpl(private val serviceApi: ServiceApi) : Repository {
    override fun loadTimeInterval(dateFrom: String, dateTo: String): Single<TimeInterval> {
        return serviceApi.loadTimeInterval(dateFrom, dateTo)
    }
}