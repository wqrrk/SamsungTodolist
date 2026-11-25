package com.example.todolist.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.presentation.ui.component.TodoItemCard
import com.example.todolist.presentation.viewmodel.TodoScreenState
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onTodoClick: (Int) -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = screenState) {

            TodoScreenState.Loading -> {
                CircularProgressIndicator()
            }

            is TodoScreenState.Content -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = state.todos,
                        key = { it.id }
                    ) { todo ->
                        TodoItemCard(
                            todo = todo,
                            onClick = {
                                onTodoClick(todo.id)
                            },
                            onToggle = {
                                viewModel.toggleTodo(todo.id)
                            }
                        )
                    }
                }
            }
        }
    }
}
