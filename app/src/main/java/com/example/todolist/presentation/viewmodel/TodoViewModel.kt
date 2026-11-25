package com.example.todolist.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.Creator
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<TodoScreenState>(
            TodoScreenState.Loading
        )

    val screenState: StateFlow<TodoScreenState> =
        _screenState.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val todos = getTodosUseCase()

            _screenState.value =
                TodoScreenState.Content(todos)
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)

            val todos = getTodosUseCase()

            _screenState.value =
                TodoScreenState.Content(todos)
        }
    }

    companion object {

        fun getViewModelFactory(
            context: Context
        ): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {

                    val (getTodos, toggleTodo) =
                        Creator.provideUseCases(context)

                    return TodoViewModel(
                        getTodos,
                        toggleTodo
                    ) as T
                }
            }
    }
}
