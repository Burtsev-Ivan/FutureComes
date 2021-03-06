package ru.burtsev.futurecomes.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.burtsev.futurecomes.statistics.repository.Repository
import ru.burtsev.futurecomes.statistics.repository.RepositoryImpl
import ru.burtsev.futurecomes.statistics.repository.TestRepositoryImpl
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule(private val baseUrl: String) {


    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize.toLong())
    }


    @Provides
    @Singleton
    internal fun provideHttpClient(cache: Cache): OkHttpClient {

        val builder = OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        return builder.build()
    }


    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    @Singleton
    @Named("RepoProd")
    internal fun provideProdRepository(serviceApi: ServiceApi): Repository {
        return RepositoryImpl(serviceApi)
    }

    @Provides
    @Singleton
    @Named("RepoTest")
    internal fun provideTestRepository(): Repository {
        return TestRepositoryImpl()
    }

    @Provides
    @Singleton
    internal fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}
