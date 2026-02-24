package com.statuscraftpro.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// কাস্টম কালার প্যালেট - যা তোর সেই কমলা লোগোর সাথে ম্যাচ করবে
val md_theme_light_primary = Color(0xFF006874)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF97F0FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001F24)
val md_theme_light_secondary = Color(0xFF4A5E62)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFCDE3E7)
val md_theme_light_onSecondaryContainer = Color(0xFF051B1F)
val md_theme_light_tertiary = Color(0xFF535E7B)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFDBE2FF)
val md_theme_light_onTertiaryContainer = Color(0xFF0F1A34)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFAFDFD)
val md_theme_light_onBackground = Color(0xFF191C1D)
val md_theme_light_surface = Color(0xFFFAFDFD)
val md_theme_light_onSurface = Color(0xFF191C1D)
val md_theme_light_surfaceVariant = Color(0xFFDBE4E6)
val md_theme_light_onSurfaceVariant = Color(0xFF3F484A)
val md_theme_light_outline = Color(0xFF6F797B)
val md_theme_light_inverseOnSurface = Color(0xFFEFF1F2)
val md_theme_light_inverseSurface = Color(0xFF2D3132)
val md_theme_light_inversePrimary = Color(0xFF4FD0E1)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006874)
val md_theme_light_outlineVariant = Color(0xFFBFC8CA)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFF4FD0E1)
val md_theme_dark_onPrimary = Color(0xFF00363E)
val md_theme_dark_primaryContainer = Color(0xFF004F58)
val md_theme_dark_onPrimaryContainer = Color(0xFF97F0FF)
val md_theme_dark_secondary = Color(0xFFB1C7CB)
val md_theme_dark_onSecondary = Color(0xFF1C3034)
val md_theme_dark_secondaryContainer = Color(0xFF33474A)
val md_theme_dark_onSecondaryContainer = Color(0xFFCDE3E7)
val md_theme_dark_tertiary = Color(0xFFBDC6E8)
val md_theme_dark_onTertiary = Color(0xFF262F4A)
val md_theme_dark_tertiaryContainer = Color(0xFF3C4562)
val md_theme_dark_onTertiaryContainer = Color(0xFFDBE2FF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF191C1D)
val md_theme_dark_onBackground = Color(0xFFE1E3E4)
val md_theme_dark_surface = Color(0xFF191C1D)
val md_theme_dark_onSurface = Color(0xFFE1E3E4)
val md_theme_dark_surfaceVariant = Color(0xFF3F484A)
val md_theme_dark_onSurfaceVariant = Color(0xFFBFC8CA)
val md_theme_dark_outline = Color(0xFF899295)
val md_theme_dark_inverseOnSurface = Color(0xFF191C1D)
val md_theme_dark_inverseSurface = Color(0xFFE1E3E4)
val md_theme_dark_inversePrimary = Color(0xFF006874)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF4FD0E1)
val md_theme_dark_outlineVariant = Color(0xFF3F484A)
val md_theme_dark_scrim = Color(0xFF000000)

// ওস্তাদ, তোর স্পেশাল OLED Black মোড
val oledBlackSurface = Color.Black
val oledBlackBackground = Color.Black

fun getColorScheme(
    darkTheme: Boolean,
    oledBlack: Boolean = false
): androidx.compose.material3.ColorScheme {
    val light = lightColorScheme(
        primary = md_theme_light_primary,
        onPrimary = md_theme_light_onPrimary,
        primaryContainer = md_theme_light_primaryContainer,
        onPrimaryContainer = md_theme_light_onPrimaryContainer,
        secondary = md_theme_light_secondary,
        onSecondary = md_theme_light_onSecondary,
        secondaryContainer = md_theme_light_secondaryContainer,
        onSecondaryContainer = md_theme_light_onSecondaryContainer,
        tertiary = md_theme_light_tertiary,
        onTertiary = md_theme_light_onTertiary,
        tertiaryContainer = md_theme_light_tertiaryContainer,
        onTertiaryContainer = md_theme_light_onTertiaryContainer,
        error = md_theme_light_error,
        onError = md_theme_light_onError,
        errorContainer = md_theme_light_errorContainer,
        onErrorContainer = md_theme_light_onErrorContainer,
        background = md_theme_light_background,
        onBackground = md_theme_light_onBackground,
        surface = md_theme_light_surface,
        onSurface = md_theme_light_onSurface,
        surfaceVariant = md_theme_light_surfaceVariant,
        onSurfaceVariant = md_theme_light_onSurfaceVariant,
        outline = md_theme_light_outline,
        inverseSurface = md_theme_light_inverseSurface,
        inverseOnSurface = md_theme_light_inverseOnSurface,
        inversePrimary = md_theme_light_inversePrimary,
        surfaceTint = md_theme_light_surfaceTint,
        outlineVariant = md_theme_light_outlineVariant,
        scrim = md_theme_light_scrim
    )

    val dark = darkColorScheme(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        primaryContainer = md_theme_dark_primaryContainer,
        onPrimaryContainer = md_theme_dark_onPrimaryContainer,
        secondary = md_theme_dark_secondary,
        onSecondary = md_theme_dark_onSecondary,
        secondaryContainer = md_theme_dark_secondaryContainer,
        onSecondaryContainer = md_theme_dark_onSecondaryContainer,
        tertiary = md_theme_dark_tertiary,
        onTertiary = md_theme_dark_onTertiary,
        tertiaryContainer = md_theme_dark_tertiaryContainer,
        onTertiaryContainer = md_theme_dark_onTertiaryContainer,
        error = md_theme_dark_error,
        onError = md_theme_dark_onError,
        errorContainer = md_theme_dark_errorContainer,
        onErrorContainer = md_theme_dark_onErrorContainer,
        background = md_theme_dark_background,
        onBackground = md_theme_dark_onBackground,
        surface = md_theme_dark_surface,
        onSurface = md_theme_dark_onSurface,
        surfaceVariant = md_theme_dark_surfaceVariant,
        onSurfaceVariant = md_theme_dark_onSurfaceVariant,
        outline = md_theme_dark_outline,
        inverseSurface = md_theme_dark_inverseSurface,
        inverseOnSurface = md_theme_dark_inverseOnSurface,
        inversePrimary = md_theme_dark_inversePrimary,
        surfaceTint = md_theme_dark_surfaceTint,
        outlineVariant = md_theme_dark_outlineVariant,
        scrim = md_theme_dark_scrim
    )

    val scheme = if (darkTheme) dark else light

    return if (darkTheme && oledBlack) {
        scheme.copy(
            background = oledBlackBackground,
            surface = oledBlackSurface,
            surfaceVariant = oledBlackSurface
        )
    } else {
        scheme
    }
}
