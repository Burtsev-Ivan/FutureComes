package ru.burtsev.futurecomes.app;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.burtsev.futurecomes.statistics.StatisticsActivity;
import ru.burtsev.futurecomes.statistics.dagger.StatisticksActivityModule;

@Module
public abstract class BuilderModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = StatisticksActivityModule.class)
    abstract StatisticsActivity stationsSelectionActivity();

}
