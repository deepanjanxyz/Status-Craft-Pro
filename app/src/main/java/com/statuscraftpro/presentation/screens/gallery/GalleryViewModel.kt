package com.statuscraftpro.presentation.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.statuscraftpro.domain.model.Screenshot
import com.statuscraftpro.domain.repository.ScreenshotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: ScreenshotRepository
) : ViewModel() {

    // repository থেকে ডেটা নিয়ে UI-এর জন্য StateFlow তে কনভার্ট করা
    val screenshots: StateFlow<List<Screenshot>> = repository.getScreenshots()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    // দরকার পড়লে ম্যানুয়ালি রিফ্রেশ করার জন্য
    fun refresh() {
        viewModelScope.launch { 
            repository.refresh() 
        }
    }
}
