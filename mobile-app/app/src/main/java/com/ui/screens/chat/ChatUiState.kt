package com.KivoFit.ui.screens.chat

data class ChatUiState(
    val title: String = "KivoBot",
    val statusLabel: String = "Asistente IA · KivoFit",
    val messages: List<ChatMessage> = emptyList(),
    val draft: String = "",
    val assistantTyping: Boolean = false,
    val error: String? = null
)

enum class ChatAuthor { User, Assistant }

data class ChatMessage(
    val id: String,
    val author: ChatAuthor,
    val text: String,
    val timeLabel: String
)
