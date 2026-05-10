package com.KivoFit.ui.screens.chat

data class ChatUiState(
    val title: String = "Asistente",
    val statusLabel: String = "en línea",
    val messages: List<ChatMessage> = emptyList(),
    val draft: String = "",
    val assistantTyping: Boolean = false
)

enum class ChatAuthor { User, Assistant }

data class ChatMessage(
    val id: String,
    val author: ChatAuthor,
    val text: String,
    val timeLabel: String
)
