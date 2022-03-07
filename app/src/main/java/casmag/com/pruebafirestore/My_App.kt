package casmag.com.pruebafirestore

import android.app.Application
import android.os.SystemClock

class My_App : Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(3000)
    }
}