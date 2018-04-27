package yu.dev.myapplication

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class MyApplication : Application() {
    private var lifecycleHandler = MyLifecycleHandler()

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
    }

    fun isAppForeground() : Boolean {
        return lifecycleHandler.isForeground()
    }
    fun isApplicationVisible(): Boolean {
        return  lifecycleHandler.isApplicationVisible()
    }
}