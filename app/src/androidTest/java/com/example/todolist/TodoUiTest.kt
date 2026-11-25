package com.example.todolist

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class TodoUiTest {

    @get:Rule
    val composeTestRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun allThreeTasksAreDisplayed() {
        composeTestRule
            .onNodeWithText("Купить молоко")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Позвонить маме")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Сделать ДЗ по Android")
            .assertIsDisplayed()
    }

    @Test
    fun checkboxChangesStatus() {
        val checkbox = composeTestRule
            .onAllNodes(isToggleable())[0]

        checkbox.assertIsOff()

        checkbox.performClick()

        checkbox.assertIsOn()

        checkbox.performClick()

        checkbox.assertIsOff()
    }

    @Test
    fun navigationListDetailList() {
        composeTestRule
            .onNodeWithText("Купить молоко")
            .performClick()

        composeTestRule
            .onNodeWithText("2 литра, обезжиренное")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Не выполнено")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("← Назад")
            .performClick()

        composeTestRule
            .onNodeWithText("Позвонить маме")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Сделать ДЗ по Android")
            .assertIsDisplayed()
    }
}
