package cz.example.base.di

import cz.example.base.prefs.PrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule = module {

    // view modely

    // preferences
    preferences()
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
}
