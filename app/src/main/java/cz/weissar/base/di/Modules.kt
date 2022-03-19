package cz.weissar.base.di

import androidx.room.Room
import cz.weissar.base.common.prefs.PrefManager
import cz.weissar.base.data.db.AppDatabase
import cz.weissar.base.data.services.DummyWebService
import cz.weissar.base.data.services.SpaceXAPI
import cz.weissar.base.di.repositories.DummyRepository
import cz.weissar.base.di.repositories.SpaceXRepo
import cz.weissar.base.ui.dummy.DummyViewModel
import cz.weissar.base.ui.spacex.SpaceXVM
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    viewModels()
}

val restModule = module {

    // retrofit & ktor REST clients
    httpClientAndConverter()
    apiRetrofit()
    apiKtor()
}
val dataModule = module {

    preferences()
    repositories()

    // db
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                AppDatabase.Name
            )
            //.addMigrations(Migration_4_to_5)
            .build()
    }

    // Dao
    single { get<AppDatabase>().dummyDao() }
}

val allModules = listOf(appModule, restModule, dataModule)

private fun Module.preferences() {
    single(createdAtStart = true) {
        PrefManager(
            androidContext()
        )
    }
}

private fun Module.apiKtor() {
    single { SpaceXAPI() }
}

private fun Module.repositories() {
    single { SpaceXRepo() }
    single { DummyRepository() }
}


private fun Module.viewModels() {
    viewModel { DummyViewModel(get()) }
    viewModel { SpaceXVM(get()) }
}

private fun Module.apiRetrofit() {
    single { DummyWebService(get()) }
}

private fun Module.httpClientAndConverter() {
    single { createRetrofit(createOkHttpClient(), "https://jsonplaceholder.typicode.com/") }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .callTimeout(10L, TimeUnit.SECONDS)
        .writeTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val restBuilder = chain.request().newBuilder()
            // Possible failure handle
            chain.proceed(restBuilder.build())
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
