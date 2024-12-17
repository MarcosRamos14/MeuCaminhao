package com.dys.mobile.meucaminhao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeuCaminhaoTheme {
                MainNavHost()
            }
        }
    }
}