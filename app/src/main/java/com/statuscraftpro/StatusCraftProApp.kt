package com.statuscraftpro

import android.app.Application
import com.statuscraftpro.data.datasource.local.MediaStoreScreenshotDataSource
import com.statuscraftpro.data.datasource.local.ScreenshotContentObserver
import com.statuscraftpro.data.repository.ScreenshotRepositoryImpl
import com.statuscraftpro.domain.repository.ScreenshotRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class StatusCraftProApp : Application() {
    
    // গ্লোবাল রিপোজিটরি ইনস্ট্যান্স
    lateinit var screenshotRepository: ScreenshotRepository
        private set

    override fun onCreate() {
        super.onCreate()
        
        val dataSource = MediaStoreScreenshotDataSource(contentResolver)
        val observer = ScreenshotContentObserver()
        val externalScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        
        screenshotRepository = ScreenshotRepositoryImpl(dataSource, observer, externalScope)
        
        // কন্টেন্ট অবজারভার রেজিস্টার করা যাতে নতুন স্ক্রিনশট এলে অ্যাপ জানতে পারে
        contentResolver.registerContentObserver(
            observer.observeUri(),
            true,
            observer
        )
    }
}
