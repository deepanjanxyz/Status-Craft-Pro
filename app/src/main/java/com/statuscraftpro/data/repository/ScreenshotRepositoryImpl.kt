package com.statuscraftpro.data.repository

import com.statuscraftpro.data.datasource.local.MediaStoreScreenshotDataSource
import com.statuscraftpro.data.datasource.local.ScreenshotContentObserver
import com.statuscraftpro.domain.model.Screenshot
import com.statuscraftpro.domain.repository.ScreenshotRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScreenshotRepositoryImpl(
    private val dataSource: MediaStoreScreenshotDataSource,
    private val observer: ScreenshotContentObserver,
    private val externalScope: CoroutineScope
) : ScreenshotRepository {

    private val _screenshots = MutableStateFlow<List<Screenshot>>(emptyList())
    override fun getScreenshots(): StateFlow<List<Screenshot>> = _screenshots.asStateFlow()

    init {
        // এবার এই লাইনটা কাজ করবে
        observer.onChange = {
            externalScope.launch { refresh() }
        }
        // শুরুর লোড
        externalScope.launch { refresh() }
    }

    override suspend fun refresh() {
        val newList = dataSource.loadScreenshots()
        withContext(Dispatchers.Main) {
            _screenshots.value = newList
        }
    }
}
