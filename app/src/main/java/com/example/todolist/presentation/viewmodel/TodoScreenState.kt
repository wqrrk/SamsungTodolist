package com.example.todolist.presentation.viewmodel

import com.example.todolist.domain.model.TodoItem

sealed class TodoScreenState {

    object Loading : TodoScreenState()

    data class Content(
        val todos: List<TodoItem>
    ) : TodoScreenState()
}
