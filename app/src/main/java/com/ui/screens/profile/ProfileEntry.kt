package com.KivoFit.ui.screens.profile

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun ProfileEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: ProfileViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()

    ProfileScreen(
        state = state,
        onChangePhoto = vm::onChangePhoto,
        onEditProfile = vm::onEditProfile,
        onChangePassword = vm::onChangePassword,
        onToggleNotifications = vm::onToggleNotifications,
        onToggleDarkMode = vm::onToggleDarkMode,
        onHelp = vm::onHelp,
        onLogout = vm::onLogout
    )
}
