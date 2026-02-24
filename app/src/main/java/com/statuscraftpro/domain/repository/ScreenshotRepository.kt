package com.statuscraftpro.domain.repository

import com.statuscraftpro.domain.model.Screenshot
import kotlinx.coroutines.flow.Flow

interface ScreenshotRepository {
    fun getScreenshots(): Flow<List<Screenshot>>
    suspend fun refresh()
}
