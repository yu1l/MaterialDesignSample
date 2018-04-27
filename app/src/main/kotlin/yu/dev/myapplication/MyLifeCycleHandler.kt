package yu.dev.myapplication

import android.app.Activity
import android.os.Bundle
import android.app.Application.ActivityLifecycleCallbacks


// 実際にActivityのLifecycleが変わった時に呼ばれるinterfaceです。
class MyLifecycleHandler : ActivityLifecycleCallbacks {

    private var isForeground = false


    // どんな方法でもいいですが、例としてintでcountしています。
    private var resumed: Int = 0
    private var paused: Int = 0
    private var started: Int = 0
    private var stopped: Int = 0

    /**
     * アプリが前面にいるかどうかを取得します.
     * @return Foregroundにいたら`true`,backgroundにいたら`false`をかえします
     */
    fun isForeground(): Boolean {
        return isForeground
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        ++resumed
//        if (activity instanceof CommonActivity) {
            isForeground = true
//        }
    }

    override fun onActivityPaused(activity: Activity) {
        ++paused
//        if (activity instanceof CommonActivity) {
            isForeground = false
//        }
        android.util.Log.w("test", "application is in foreground: " + (resumed > paused))
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityStarted(activity: Activity) {
        ++started
    }

    override fun onActivityStopped(activity: Activity) {
        ++stopped
        android.util.Log.w("test", "application is visible: " + (started > stopped))
    }

    fun isApplicationVisible(): Boolean {
        return started > stopped
    }

    // もし必要ならstaticにして参照できるようにすれば、Serviceなどから状態の確認ができます。
//    private static int resumed;
//    private static int paused;
//    private static int started;
//    private static int stopped;

//    public static boolean isApplicationVisible() {
//        return started > stopped;
//    }
//
//    public static boolean isApplicationInForeground() {
//        return resumed > paused;
//    }
}