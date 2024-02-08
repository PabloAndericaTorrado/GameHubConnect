package com.pabloat.apiandroid.ui.util

import com.pabloat.apiandroid.data.local.Categoria
import kotlinx.coroutines.flow.Flow

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()
    data class Success(val weathers: Flow<List<Categoria>>) : ScreenState()
}
