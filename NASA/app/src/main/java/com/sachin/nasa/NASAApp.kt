package com.sai.fabula

import android.app.Application
import com.sai.fabula.database.APODRepository
import com.sai.fabula.di.ApiModule
import com.sachin.nasa.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NASAApp : Application() {

    private var koinModuleList = module {
        single { ApiModule() }
        single { APODRepository(get()) }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
      //  initTimber()
        initKoin()

    }


    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NASAApp)
            modules(koinModuleList)
        }
    }

//    private fun initTimber() {
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }
//    }
}
