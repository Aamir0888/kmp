package org.example.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.HomeNav
import screens.LoginScreen

val lightRedColor = Color(0xFFF57D88)
val darkRedColor = Color(0xFF77000B)

enum class Screen {
    Home, Detail
}

@Composable
@Preview
fun App() {
    val lightColors = lightColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val darkColors = darkColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) darkColors else lightColors
    )

    MaterialTheme(colorScheme = colors) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            HomeNav()
//            LoginScreen()
//            PostScreen()
//            var currentScreen by remember { mutableStateOf(Screen.Home) }
//            var selectedId by remember { mutableStateOf("") }
//            when (currentScreen) {
//                Screen.Home -> {
//                    HomeScreen {
//                        currentScreen = Screen.Detail
//                        selectedId = it.idMeal
//                    }
//                }
//
//                Screen.Detail -> {
//                    DetailScreen(
//                        id = selectedId,
//                        navigateBack = {
//                            currentScreen = Screen.Home
//                            selectedId = ""
//                        }
//                    )
//                }
//            }
        }
    }
}