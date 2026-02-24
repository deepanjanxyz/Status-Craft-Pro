package com.statuscraftpro.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {

    // OLED Black Mode-এর স্টেট
    private val _oledBlack = MutableStateFlow(false)
    val oledBlack: StateFlow<Boolean> = _oledBlack.asStateFlow()

    // Dynamic Color-এর স্টেট (ডিফল্ট ট্রু রাখা হলো)
    private val _dynamicColor = MutableStateFlow(true)
    val dynamicColor: StateFlow<Boolean> = _dynamicColor.asStateFlow()

    fun setOledBlack(enabled: Boolean) {
        _oledBlack.value = enabled
    }

    fun setDynamicColor(enabled: Boolean) {
        _dynamicColor.value = enabled
    }
}
