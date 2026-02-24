package com.statuscraftpro.data.datasource.local

import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore

class ScreenshotContentObserver : ContentObserver(Handler(Looper.getMainLooper())) {

    // এটাকে var করলাম যাতে রিপোজিটরি থেকে সেট করা যায়
    var onChange: (() -> Unit)? = null

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        onChange?.invoke()
    }

    fun observeUri(): Uri {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    }
}
