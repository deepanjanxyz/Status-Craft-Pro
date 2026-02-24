package com.statuscraftpro.data.datasource.local

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.statuscraftpro.domain.model.Screenshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaStoreScreenshotDataSource(
    private val contentResolver: ContentResolver
) {
    suspend fun loadScreenshots(): List<Screenshot> = withContext(Dispatchers.IO) {
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        // Filter: only images inside the "Screenshots" folder
        // Corrected BUCKEL to BUCKET
        val selection = "${MediaStore.Images.Media.BUCKET_DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf("Screenshots")

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        val screenshots = mutableListOf<Screenshot>()

        contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(collection, id)
                
                screenshots.add(
                    Screenshot(
                        id = id,
                        uri = uri,
                        fileName = cursor.getString(nameColumn),
                        dateTaken = cursor.getLong(dateColumn),
                        width = cursor.getInt(widthColumn),
                        height = cursor.getInt(heightColumn)
                    )
                )
            }
        }
        return@withContext screenshots
    }
}
