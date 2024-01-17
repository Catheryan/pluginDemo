package com.example.plugin_app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import top.guuguo.plugin_base.IPluginActivityInterface
import com.example.plugin_app.ui.theme.MyApplicationTheme
import top.guuguo.plugin_base.utils.ext.logI

class PluginsActivity : IPluginActivityInterface() {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        (owner as? ComponentActivity)?.apply {
            setContent {
                MyApplicationTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        MainContent()
                    }
                }
            }
        }
        "currentState==>${owner.lifecycle.currentState}".logI()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        "currentState==>${owner.lifecycle.currentState}".logI()
    }
}

@Composable
fun MainContent() {
    val context = LocalContext.current
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "我是插件app", modifier = Modifier.clickable {
            throw RuntimeException("点击测试crash")
        })
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainContent()
    }
}