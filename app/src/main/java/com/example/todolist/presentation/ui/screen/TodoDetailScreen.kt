package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.TodoItem

@Composable
fun TodoDetailScreen(
    todo: TodoItem,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        TextButton(
            onClick = onBackClick
        ) {
            Text("← Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = todo.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = todo.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (todo.isCompleted) {
                "Выполнено"
            } else {
                "Не выполнено"
            },
            color = if (todo.isCompleted) {
                Color(0xFF388E3C)
            } else {
                MaterialTheme.colorScheme.error
            },
            style = MaterialTheme.typography.titleMedium
        )
    }
}
