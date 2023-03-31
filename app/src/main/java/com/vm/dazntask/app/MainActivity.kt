package com.vm.dazntask.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vm.dazntask.app.ui.MainScreen
import com.vm.dazntask.app.ui.theme.DaznTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaznTaskTheme {
                MainScreen()
            }
        }
    }
}
