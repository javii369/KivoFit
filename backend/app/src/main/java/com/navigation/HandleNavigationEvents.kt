package com.KivoFit.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow

@Composable
fun HandleNavigationEvents(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    events: Flow<UiEvent>
) {
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navController.navigate(event.route) {
                        launchSingleTop = event.singleTop
                        event.popUpTo?.let { target ->
                            popUpTo(target) { inclusive = event.inclusive }
                        }
                    }
                }
                is UiEvent.NavigateBack -> navController.popBackStack()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
}
