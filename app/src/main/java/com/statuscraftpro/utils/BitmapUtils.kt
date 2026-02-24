package com.statuscraftpro.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.statuscraftpro.domain.model.OverlayElement
import com.statuscraftpro.domain.model.OverlayType
import java.io.OutputStream

object BitmapUtils {

    /**
     * অরিজিনাল স্ক্রিনশটের ওপর এডিট করা এলিমেন্টগুলো বসানো
     */
    fun applyOverlays(
        original: ImageBitmap,
        overlays: List<OverlayElement>,
        canvasWidth: Int,
        canvasHeight: Int
    ): ImageBitmap {
        val resultBitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(resultBitmap)

        // অরিজিনাল ইমেজ ড্র করা
        val originalBitmap = original.asAndroidBitmap()
        val matrix = Matrix()
        matrix.setRectToRect(
            android.graphics.RectF(0f, 0f, originalBitmap.width.toFloat(), originalBitmap.height.toFloat()),
            android.graphics.RectF(0f, 0f, canvasWidth.toFloat(), canvasHeight.toFloat()),
            Matrix.ScaleToFit.CENTER
        )
        canvas.drawBitmap(originalBitmap, matrix, Paint(Paint.ANTI_ALIAS_FLAG))

        // প্রত্যেকটা ওভারলে (ঘড়ি, ব্যাটারি ইত্যাদি) ড্র করা
        overlays.forEach { overlay ->
            drawOverlay(canvas, overlay)
        }

        return resultBitmap.asImageBitmap()
    }

    private fun drawOverlay(canvas: Canvas, overlay: OverlayElement) {
        val paint = Paint().apply {
            color = android.graphics.Color.RED // আপাতত লাল রঙ, পরে এটা ডাইনামিক হবে
            style = Paint.Style.FILL
            textSize = 40f
            isAntiAlias = true
        }

        canvas.save()
        canvas.translate(overlay.position.x, overlay.position.y)
        canvas.scale(overlay.scale, overlay.scale)
        canvas.rotate(overlay.rotation)

        when (overlay.type) {
            OverlayType.CLOCK -> canvas.drawText("12:00", 0f, 0f, paint)
            OverlayType.BATTERY -> canvas.drawText("100%", 0f, 0f, paint)
            OverlayType.NETWORK_4G -> canvas.drawText("4G", 0f, 0f, paint)
            OverlayType.NETWORK_5G -> canvas.drawText("5G", 0f, 0f, paint)
            OverlayType.NETWORK_WIFI -> canvas.drawText("Wi-Fi", 0f, 0f, paint)
            OverlayType.CUSTOM_TEXT -> {
                overlay.customText?.let { canvas.drawText(it, 0f, 0f, paint) }
            }
        }
        canvas.restore()
    }

    /**
     * এডিট করা ছবি গ্যালারিতে সেভ করা
     */
    fun saveBitmapToGallery(
        context: Context,
        bitmap: ImageBitmap,
        filename: String = "StatusCraft_${System.currentTimeMillis()}.jpg"
    ): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/StatusCraftPro")
            }
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            resolver.openOutputStream(it).use { outputStream: OutputStream? ->
                if (outputStream != null) {
                    val androidBitmap = bitmap.asAndroidBitmap()
                    androidBitmap.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)
                    outputStream.flush()
                }
            }
        }

        return uri
    }
}
