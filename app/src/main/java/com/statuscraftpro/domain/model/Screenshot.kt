package com.statuscraftpro.domain.model

import android.net.Uri

data class Screenshot(
    val id: Long,
    val uri: Uri,
    val fileName: String,
    val dateTaken: Long,
    val width: Int,
    val height: Int
)
