package com.KivoFit.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatScreen(
    state: ChatUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val s = KivoFitTheme.spacing
    var messageText by remember { mutableStateOf("") }
    val currentTime = remember {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A1635),
                        Color(0xFF13284D)
                    )
                )
            )
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A2A47))
                .padding(horizontal = s.lg, vertical = s.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AssistantAvatar(size = 56.dp)

            Spacer(modifier = Modifier.width(s.md))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = state.title.ifBlank { "Asistente IA" },
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF00E56B), CircleShape)
                    )
                    Text(
                        text = "En linea",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF00E56B)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = s.lg, vertical = s.lg)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                AssistantAvatar(size = 34.dp)
                Spacer(modifier = Modifier.width(s.sm))

                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4A67))
                ) {
                    Column(modifier = Modifier.padding(horizontal = s.md, vertical = s.md)) {
                        Text(
                            text = "Hola! Soy tu asistente virtual de fitness. En que puedo ayudarte hoy?",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(s.xs))
                        Text(
                            text = currentTime,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFA8B3CF)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Preguntas frecuentes:",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFA8B3CF)
            )

            Spacer(modifier = Modifier.height(s.sm))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.sm)
            ) {
                SuggestionChip(
                    text = "Como crear una rutina?",
                    onClick = { messageText = "Como crear una rutina?" },
                    modifier = Modifier.weight(1f)
                )
                SuggestionChip(
                    text = "Consejos de dieta",
                    onClick = { messageText = "Consejos de dieta" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(s.sm))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.sm)
            ) {
                SuggestionChip(
                    text = "Cuanta proteina necesito?",
                    onClick = { messageText = "Cuanta proteina necesito?" },
                    modifier = Modifier.weight(1f)
                )
                SuggestionChip(
                    text = "Ejercicios para abdomen",
                    onClick = { messageText = "Ejercicios para abdomen" },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .border(1.dp, Color(0xFF2A3F66)),
            color = Color(0xFF1A2A47)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = s.lg, vertical = s.md),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(s.sm)
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text(
                            text = "Escribe tu pregunta...",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFFA8B3CF)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF3B4A67),
                        unfocusedContainerColor = Color(0xFF3B4A67),
                        disabledContainerColor = Color(0xFF3B4A67),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true
                )

                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(Color(0xFF6933F7), RoundedCornerShape(12.dp))
                        .clickable { 
                            // Aquí se enviaría el mensaje
                            if (messageText.isNotBlank()) {
                                messageText = ""
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Enviar",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun AssistantAvatar(size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(Color(0xFF6933F7), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.SmartToy,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun SuggestionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(50),
        color = Color(0xFF3B4A67)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
        )
    }
}
