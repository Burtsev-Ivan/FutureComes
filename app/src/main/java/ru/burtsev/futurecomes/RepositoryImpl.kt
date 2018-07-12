package ru.burtsev.futurecomes

import io.reactivex.Single
import ru.burtsev.futurecomes.data.TimeInterval
import ru.burtsev.futurecomes.network.ServiceApi

class RepositoryImpl(private val serviceApi: ServiceApi) : Repository {
    override fun loadTimeInterval(dateFrom: String, dateTo: String): Single<TimeInterval> {
        return serviceApi.loadTimeInterval(dateFrom, dateTo)
    }
}