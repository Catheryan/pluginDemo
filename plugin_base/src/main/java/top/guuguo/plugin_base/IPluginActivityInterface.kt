package top.guuguo.plugin_base

import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver

abstract class IPluginActivityInterface  : DefaultLifecycleObserver {
    lateinit var mHostActivity: HostActivity
    fun registerHostActivity(hostActivity: HostActivity) {
        mHostActivity = hostActivity;
    }

    fun getIntent() = mHostActivity.intent
    fun setContentView(layoutResID: Int) {
        mHostActivity.setContentView(layoutResID)
    }
}