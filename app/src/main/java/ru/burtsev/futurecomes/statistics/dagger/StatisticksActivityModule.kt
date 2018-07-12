package ru.burtsev.futurecomes.statistics.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.burtsev.futurecomes.app.FragmentScoped
import ru.burtsev.futurecomes.statistics.StatisticsFragment

@Module
abstract class StatisticksActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun statisticksFragment(): StatisticsFragment


}
