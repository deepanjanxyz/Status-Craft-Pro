package com.statuscraftpro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.statuscraftpro.presentation.navigation.AppNavHost
import com.statuscraftpro.presentation.theme.StatusCraftProTheme
import com.statuscraftpro.presentation.viewmodel.EditorViewModel
import com.statuscraftpro.presentation.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ভিউমডেলগুলো ইনিশিয়ালাইজ করা
            val settingsViewModel: SettingsViewModel = viewModel()
            val editorViewModel: EditorViewModel = viewModel()

            // সেটিংস থেকে ডেটা নিয়ে রিয়েল-টাইমে থিমে অ্যাপ্লাই করা
            val oledBlack by settingsViewModel.oledBlack.collectAsState()
            val dynamicColor by settingsViewModel.dynamicColor.collectAsState()

            StatusCraftProTheme(
                oledBlack = oledBlack,
                dynamicColor = dynamicColor
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // নেভিগেশন হোস্ট সেটআপ করা
                    AppNavHost(
                        editorViewModel = editorViewModel,
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }
}
