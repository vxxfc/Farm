package com.farmmanager.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.farmmanager.app.ui.navigation.AppNavigation
import com.farmmanager.app.ui.theme.FarmManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as FarmApp).repository

        setContent {
            FarmManagerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(repository = repository)
                }
            }
        }
    }
}
