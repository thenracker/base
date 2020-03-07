package cz.example.base.di

import cz.example.base.prefs.PrefManager
import cz.example.base.ui.schedule.ScheduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


val appModule = module {

    // view modely

    // preferences
    preferences()

    schedule()
}
val restModule = module {

    // retrofit
}
val dbModule = module {

    // db
}

val allModules = listOf(appModule, restModule, dbModule)

private fun Module.preferences() {
    single(createdAtStart = true) { PrefManager(androidContext()) }
    //single(named("MySecondPrefs") createdAtStart = true) { PrefManager(androidContext()) }
}

private fun Module.schedule() {
    // ToDo - scheduleRepo
    viewModel { ScheduleViewModel() }
}
