package ru.burtsev.futurecomes.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.burtsev.futurecomes.data.TimeInterval

interface ServiceApi {

    @GET("/interval/{dateFrom}/{dateTo}")
    fun loadTimeInterval(@Path("dateFrom") dateFrom: String, @Path("dateTo") dateTo: String): Single<TimeInterval>

}