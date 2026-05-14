package com.ui

package com.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ai.models.Message
import com.ai.models.MessageRole
import com.ai.viewmodel.AiChatViewModel
import com.ai.api.ApiClient
import com.ai.repository.AiRepository
import com.ai.viewmodel.AiChatViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiChatScreen(
    onNavigateBack: () -> Unit
) {
    val repository = remember { AiRepository(ApiClient.aiApiService) }
    val factory = remember { AiChatViewModelFactory(repository) }
    val viewModel: AiChatViewModel = viewModel(factory = factory)

    val messages by viewModel.messages.observeAsState(emptyList())
    val title by viewModel.conversationTitle.observeAsState("Asistente IA")
    val error by viewModel.error.observeAsState()

    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()

    // Iniciar conversación
    LaunchedEffect(Unit) {
        viewModel.startNewConversation()
    }

    // Auto-scroll al último mensaje
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Lista de mensajes
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message)
                }
            }

            // Input de mensaje
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Escribe tu pregunta...") },
                        maxLines = 4
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            if (messageText.text.isNotBlank()) {
                                viewModel.sendMessage(messageText.text)
                                messageText = TextFieldValue("")
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Send,
                            contentDescription = "Enviar",
                            tint = Color(0xFF6200EE)
                        )
                    }
                }
            }
        }

        // Mostrar errores
        error?.let { errorMessage ->
            LaunchedEffect(errorMessage) {
                // Aquí podrías mostrar un Snackbar si lo necesitas
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.role == MessageRole.USER) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (message.role == MessageRole.USER) {
                    Color(0xFF6200EE)
                } else {
                    Color.White
                }
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            if (message.isLoading) {
                Box(
                    modifier = Modifier.padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
            } else {
                Text(
                    text = message.content,
                    modifier = Modifier.padding(12.dp),
                    color = if (message.role == MessageRole.USER) {
                        Color.White
                    } else {
                        Color(0xFF212121)
                    }
                )
            }
        }
    }
}