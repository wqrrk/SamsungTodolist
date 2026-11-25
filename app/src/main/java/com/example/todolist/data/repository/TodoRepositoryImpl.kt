
package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dataSource: TodoJsonDataSource
) : TodoRepository {

    private val todos = dataSource.getTodos().map {
        TodoItem(
            id = it.id,
            title = it.title,
            description = it.description,
            isCompleted = it.isCompleted
        )
    }.toMutableList()

    override suspend fun getTodos(): List<TodoItem> {
        return todos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todos.indexOfFirst { it.id == id }

        if (index != -1) {
            val todo = todos[index]

            todos[index] = todo.copy(
                isCompleted = !todo.isCompleted
            )
        }
    }
}
