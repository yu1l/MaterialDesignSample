package yu.dev.myapplication.module

import android.app.Activity
import yu.dev.myapplication.R

val SettingList = arrayListOf("よくあるご質問", "開発者への要望/クレーム", "プライバシーポリシー", "利用規約")

class AnimationModule {
    enum class Type(val rawValue: Int) {
        STANDARD(1 shl 1),
        TWITTER(1 shl 2)
    }
    enum class State(val rawValue: Int) {
        START(1 shl 1),
        BACK(1 shl 2)
    }
    companion object {
        fun with(activity: Activity): Instance {
            return Instance(activity)
        }
    }

    class Instance(activity: Activity) {
        private var mTarget: Activity = activity
        private enum class Anim(val rawValue: Int) {
            STANDARD_START(Type.STANDARD.rawValue shl State.START.rawValue),
            STANDARD_BACK(Type.STANDARD.rawValue shl State.BACK.rawValue),
            TWITTER_START(Type.TWITTER.rawValue shl State.START.rawValue),
            TWITTER_BACK(Type.TWITTER.rawValue shl State.BACK.rawValue)
        }

        fun animate(type: Type, state: State) {
            when (type.rawValue shl state.rawValue) {
                Anim.STANDARD_START.rawValue -> this.mTarget.overridePendingTransition(R.anim.login_activity_start_enter, R.anim.login_activity_start_exit)
                Anim.STANDARD_BACK.rawValue -> this.mTarget.overridePendingTransition(R.anim.login_activity_back_enter, R.anim.login_activity_back_exit)
                Anim.TWITTER_START.rawValue ->  this.mTarget.overridePendingTransition(R.anim.twitter_login_activity_start_enter, R.anim.twitter_login_activity_start_exit)
                Anim.TWITTER_BACK.rawValue -> this.mTarget.overridePendingTransition(R.anim.twitter_login_activity_back_enter, R.anim.twitter_login_activity_back_exit)
            }
        }
    }
}