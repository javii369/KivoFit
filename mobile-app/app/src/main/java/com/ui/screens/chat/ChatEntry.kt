package com.KivoFit.ui.screens.chat

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun ChatEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: ChatViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()

    ChatScreen(
        state = state,
        onDraftChange = vm::onDraftChange,
        onSend = vm::onSend,
        onErrorDismissed = vm::onErrorDismissed
    )
}
