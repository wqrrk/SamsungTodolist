package com.example.todolist.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.presentation.ui.screen.TodoListScreen
import com.example.todolist.presentation.ui.screen.TodoDetailScreen
import com.example.todolist.presentation.viewmodel.TodoScreenState
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun TodoNavGraph(
    viewModel: TodoViewModel,
    navController: NavHostController = rememberNavController()
) {
    val screenState by viewModel.screenState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id")

            val state = screenState

            if (state is TodoScreenState.Content) {
                val todo = state.todos.find { it.id == id }

                if (todo != null) {
                    TodoDetailScreen(
                        todo = todo,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
