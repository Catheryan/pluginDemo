package top.guuguo.plugin_base

import android.os.Bundle
import androidx.activity.ComponentActivity


class HostActivity : ComponentActivity() {
    var pluginActivity: IPluginActivityInterface? = null

    companion object {
        val ARG_PLUGIN_CLASS_NAME = "ARG_PLUGIN_CLASS_NAME"
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PluginLoader.loadPlugin()
        intent.getStringExtra(ARG_PLUGIN_CLASS_NAME)?.let {
            val clazz= pluginClassLoader.loadClass(it)
            pluginActivity = clazz.newInstance() as? IPluginActivityInterface
            pluginActivity?.registerHostActivity(this)
        }
        pluginActivity?.let { lifecycle.addObserver(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        pluginActivity?.let { lifecycle.removeObserver(it) }
    }
}