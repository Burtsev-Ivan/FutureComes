package ru.burtsev.futurecomes.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.burtsev.futurecomes.R
import ru.burtsev.futurecomes.network.NetworkModule

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .network(NetworkModule(getString(R.string.server_url)))
                .application(this).build()

    }

}