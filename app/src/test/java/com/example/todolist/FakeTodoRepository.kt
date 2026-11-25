
package com.example.todolist

import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository

class FakeTodoRepository : TodoRepository {

    private val todos = mutableListOf(
        TodoItem(
            1,
            "Купить молоко",
            "2 литра, обезжиренное",
            false
        ),
        TodoItem(
            2,
            "Позвонить маме",
            "Спросить про выходные",
            true
        ),
        TodoItem(
            3,
            "Сделать ДЗ по Android",
            "Clean Architecture + Compose",
            false
        )
    )

    override suspend fun getTodos(): List<TodoItem> {
        return todos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todos.indexOfFirst { it.id == id }

        if (index != -1) {
            todos[index] = todos[index].copy(
                isCompleted = !todos[index].isCompleted
            )
        }
    }
}
