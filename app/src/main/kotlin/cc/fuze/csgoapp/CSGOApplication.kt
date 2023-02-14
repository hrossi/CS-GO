package cc.fuze.csgoapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class CSGOApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        forceDarkMode()
    }

    private fun forceDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}