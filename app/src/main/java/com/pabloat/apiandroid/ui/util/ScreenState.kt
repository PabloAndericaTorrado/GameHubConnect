package com.antpaniagua.basicretrofit.ui.util

import com.antpaniagua.basicretrofit.data.local.Weather
import kotlinx.coroutines.flow.Flow

/**
 * ScreenState
 *
 * Esta clase sealed contiene un numero de estados cerrado y limitados. Se usa con frecuencia
 * para definir un número concreto de estados.
 *
 * El formato más habitual es el que sigue, donde creamos un objeto Loading que puede tomar dos
 * estados (Success o Error). En caso de Success se muestran los datos indicados. En caso de error
 * un mensaje concreto de error. En la recomposición se tomará una vía u otra.
 *
 * Accedemos desde el viewmodel.
 */
sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()
    data class Success(val weathers: Flow<List<Weather>>) : ScreenState()
}
