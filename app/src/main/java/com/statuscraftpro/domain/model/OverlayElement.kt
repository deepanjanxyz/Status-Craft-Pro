package com.statuscraftpro.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector

data class OverlayElement(
    val id: Long,
    val type: OverlayType,
    val position: Offset,
    val scale: Float,
    val rotation: Float,
    val isSelected: Boolean,
    val customText: String? = null,
    val icon: ImageVector? = null
)
