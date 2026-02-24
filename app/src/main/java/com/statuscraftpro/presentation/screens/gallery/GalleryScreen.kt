package com.statuscraftpro.presentation.screens.gallery

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel
) {
    // screenshots ডেটা অবজার্ভ করা
    val screenshots by viewModel.screenshots.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // ৩ কলামের গ্রিড
            modifier = Modifier.fillMaxSize()
        ) {
            items(screenshots) { screenshot ->
                AsyncImage(
                    model = screenshot.uri,
                    contentDescription = screenshot.fileName, // displayName থেকে fileName-এ ফিক্সড
                    modifier = Modifier
                        .aspectRatio(1f) // ছবিগুলো যেন চৌকো দেখায়
                        .padding(2.dp),
                    contentScale = ContentScale.Crop // ছবি যেন ফ্রেমের ভেতর ঠিকঠাক বসে
                )
            }
        }
    }
}
