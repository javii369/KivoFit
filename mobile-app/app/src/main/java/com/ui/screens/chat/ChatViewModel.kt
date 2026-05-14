package com.KivoFit.ui.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.repository.chat.ChatRepository
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        ChatUiState(
            messages = listOf(
                ChatMessage(
                    id = "welcome",
                    author = ChatAuthor.Assistant,
                    text = "¡Hola! Soy KivoBot, tu asistente personal de fitness. ¿En qué puedo ayudarte hoy? Puedo orientarte sobre rutinas, nutrición, recuperación y mucho más.",
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
        if (text.isEmpty() || _state.value.assistantTyping) return

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
                assistantTyping = true,
                error = null
            )
        }

        viewModelScope.launch {
            val result = chatRepository.send(text)
            result.fold(
                onSuccess = { reply ->
                    _state.update {
                        it.copy(
                            messages = it.messages + ChatMessage(
                                id = "a-${System.currentTimeMillis()}",
                                author = ChatAuthor.Assistant,
                                text = reply,
                                timeLabel = currentTimeLabel()
                            ),
                            assistantTyping = false
                        )
                    }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            assistantTyping = false,
                            error = error.message ?: "Error al conectar con el asistente"
                        )
                    }
                }
            )
        }
    }

    fun onErrorDismissed() {
        _state.update { it.copy(error = null) }
    }

    private fun currentTimeLabel(): String =
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
}
