package com.statuscraftpro.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.statuscraftpro.presentation.screens.editor.EditorScreen
import com.statuscraftpro.presentation.screens.gallery.GalleryScreen
import com.statuscraftpro.presentation.screens.settings.SettingsScreen
import com.statuscraftpro.presentation.screens.editor.EditorViewModel
import com.statuscraftpro.presentation.screens.settings.SettingsViewModel

sealed class Screen(val route: String) {
    data object Gallery : Screen("gallery")
    data object Editor : Screen("editor/{imageUri}") {
        fun passUri(uri: String) = "editor/${Uri.encode(uri)}"
    }
    data object Settings : Screen("settings")
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Gallery.route,
    editorViewModel: EditorViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // ১. গ্যালারি স্ক্রিন
        composable(Screen.Gallery.route) {
            GalleryScreen(
                onImageClick = { uri ->
                    navController.navigate(Screen.Editor.passUri(uri.toString()))
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        // ২. এডিটর স্ক্রিন (ব্যাক অ্যারো লজিকসহ)
        composable(Screen.Editor.route) { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri")
            EditorScreen(
                imageUri = imageUri,
                viewModel = editorViewModel,
                onBackPressed = { navController.popBackStack() }
            )
        }

        // ৩. সেটিংস স্ক্রিন (ব্যাক অ্যারো লজিকসহ)
        composable(Screen.Settings.route) {
            SettingsScreen(
                viewModel = settingsViewModel,
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
