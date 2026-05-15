package com.KivoFit.navigation

sealed class UiEvent {
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : UiEvent()

    data object NavigateBack : UiEvent()

    data class ShowSnackbar(val message: String) : UiEvent()
}
