package com.example.todolist

import android.content.Context
import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.repository.TodoRepositoryImpl
import com.example.todolist.domain.repository.TodoRepository
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase

object Creator {

    private fun getRepository(context: Context): TodoRepository {
        return TodoRepositoryImpl(
            TodoJsonDataSource(context.applicationContext)
        )
    }

    fun provideUseCases(context: Context):
            Pair<GetTodosUseCase, ToggleTodoUseCase> {

        val repository = getRepository(context)

        return Pair(
            GetTodosUseCase(repository),
            ToggleTodoUseCase(repository)
        )
    }
}
