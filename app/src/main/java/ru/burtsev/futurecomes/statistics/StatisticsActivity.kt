package ru.burtsev.futurecomes.statistics

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.burtsev.futurecomes.R
import ru.burtsev.futurecomes.replaceFragmentInActivity

class StatisticsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.fragment)
                ?: StatisticsFragment().let {
                    replaceFragmentInActivity(it, R.id.fragment)
                }
    }
}
