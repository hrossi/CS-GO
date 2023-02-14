package cc.fuze.csgoapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CSGOApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin()
        forceDarkMode()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CSGOApplication)
            modules(
                listOf()
            )
        }
    }

    private fun forceDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}