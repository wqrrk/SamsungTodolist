package com.example.todolist

import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TodoUseCaseTest {

    private lateinit var repository: FakeTodoRepository
    private lateinit var getTodosUseCase: GetTodosUseCase
    private lateinit var toggleTodoUseCase: ToggleTodoUseCase

    @Before
    fun setup() {
        repository = FakeTodoRepository()
        getTodosUseCase = GetTodosUseCase(repository)
        toggleTodoUseCase = ToggleTodoUseCase(repository)
    }

    @Test
    fun getTodosReturnsThreeTasks() = runTest {
        val todos = getTodosUseCase()

        assertEquals(3, todos.size)
    }

    @Test
    fun toggleTodoChangesIsCompleted() = runTest {
        val before = getTodosUseCase()
            .first { it.id == 1 }
            .isCompleted

        toggleTodoUseCase(1)

        val after = getTodosUseCase()
            .first { it.id == 1 }
            .isCompleted

        assertEquals(!before, after)
    }
}
