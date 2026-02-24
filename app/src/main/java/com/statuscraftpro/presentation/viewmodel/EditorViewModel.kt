package com.statuscraftpro.presentation.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.statuscraftpro.domain.model.OverlayElement
import com.statuscraftpro.domain.model.OverlayType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditorViewModel : ViewModel() {

    private val _originalBitmap = MutableStateFlow<ImageBitmap?>(null)
    val originalBitmap: StateFlow<ImageBitmap?> = _originalBitmap.asStateFlow()

    private val _overlays = MutableStateFlow<List<OverlayElement>>(emptyList())
    val overlays: StateFlow<List<OverlayElement>> = _overlays.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    fun setOriginalBitmap(bitmap: ImageBitmap?) {
        _originalBitmap.value = bitmap
    }

    // ওভারলে যোগ করা (ঘড়ি, ব্যাটারি ইত্যাদি)
    fun addOverlay(type: OverlayType, position: Offset = Offset.Zero) {
        val newOverlay = OverlayElement(
            id = System.currentTimeMillis(),
            type = type,
            position = position,
            scale = 1f,
            rotation = 0f,
            isSelected = false
        )
        _overlays.update { it + newOverlay }
    }

    fun updateOverlayPosition(id: Long, newPosition: Offset) {
        _overlays.update { list ->
            list.map { if (it.id == id) it.copy(position = newPosition) else it }
        }
    }

    fun selectOverlay(id: Long) {
        _overlays.update { list ->
            list.map { it.copy(isSelected = it.id == id) }
        }
    }

    fun clearSelection() {
        _overlays.update { list ->
            list.map { it.copy(isSelected = false) }
        }
    }

    fun removeOverlay(id: Long) {
        _overlays.update { list -> list.filterNot { it.id == id } }
    }

    fun saveImage() {
        viewModelScope.launch {
            _isSaving.value = true
            // এখানে আসল সেভ করার লজিক BitmapUtils থেকে কল হবে
            _isSaving.value = false
        }
    }
}
