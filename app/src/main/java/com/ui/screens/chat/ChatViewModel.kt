package com.KivoFit.ui.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.KivoFit.navigation.UiEvent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        ChatUiState(
            messages = listOf(
                ChatMessage(
                    id = "welcome",
                    author = ChatAuthor.Assistant,
                    text = "¡Hola! Soy tu asistente de KivoFit. ¿En qué puedo ayudarte hoy?",
                    timeLabel = currentTimeLabel()
                )
            )
        )
    )
    val state: StateFlow<ChatUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onDraftChange(value: String) {
        _state.update { it.copy(draft = value) }
    }

    fun onSend() {
        val text = _state.value.draft.trim()
        if (text.isEmpty()) return

        val userMessage = ChatMessage(
            id = "u-${System.currentTimeMillis()}",
            author = ChatAuthor.User,
            text = text,
            timeLabel = currentTimeLabel()
        )
        _state.update {
            it.copy(
                messages = it.messages + userMessage,
                draft = "",
                assistantTyping = true
            )
        }

        viewModelScope.launch {
            delay(900)
            val reply = ChatMessage(
                id = "a-${System.currentTimeMillis()}",
                author = ChatAuthor.Assistant,
                text = "Recibido. En cuanto conectemos el modelo te responderé al instante.",
                timeLabel = currentTimeLabel()
            )
            _state.update {
                it.copy(
                    messages = it.messages + reply,
                    assistantTyping = false
                )
            }
        }
    }

    private fun currentTimeLabel(): String =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
}
