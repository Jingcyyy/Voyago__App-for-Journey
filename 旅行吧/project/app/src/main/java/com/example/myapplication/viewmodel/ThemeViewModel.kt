package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.GreenTheme
import com.example.myapplication.ui.theme.ThemeColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel: ViewModel() {
    private val _ThemeColor = MutableStateFlow(GreenTheme)
    val ThemeColor : StateFlow<ThemeColor> = _ThemeColor;
    fun updateColor(themeColor: ThemeColor){
        viewModelScope.launch {
            _ThemeColor.value = themeColor;
        }
    }
}