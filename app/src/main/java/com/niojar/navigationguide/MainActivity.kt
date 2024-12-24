package com.niojar.navigationguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.niojar.navigationguide.core.navigation.NavigationWrapper
import com.niojar.navigationguide.ui.theme.NavigationGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationGuideTheme {
                NavigationWrapper()
            }
        }
    }
}

