package com.truesparrow.sales

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLogInScreen() {


        composeTestRule.setContent {
//            LogInScreen();
        }


        // Perform interactions and assertions here
        composeTestRule.onNodeWithTag("salesforce_button")
            .assertExists()
            .assertIsDisplayed()
    }

}