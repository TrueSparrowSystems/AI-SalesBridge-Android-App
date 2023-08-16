package com.truesparrow.sales.services

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.truesparrow.sales.BuildConfig
import com.truesparrow.sales.screens.AccountDetails
import com.truesparrow.sales.screens.HomeScreen
import com.truesparrow.sales.screens.SplashScreen
import com.truesparrow.sales.screens.LogInScreen
import com.truesparrow.sales.screens.NoteDetailScreen
import com.truesparrow.sales.screens.NotesScreen
import com.truesparrow.sales.screens.SettingsScreen
import com.truesparrow.sales.util.Screens
import com.truesparrow.sales.viewmodals.AuthenticationViewModal


object NavigationService {
    private lateinit var navController: NavController
    private lateinit var authenticationViewModal: AuthenticationViewModal;

    /**
     * Initializes the NavigationService with the NavHostController.
     */
    fun initialize(navController: NavController, authenticationViewModal: AuthenticationViewModal) {
        this.navController = navController
        this.authenticationViewModal = authenticationViewModal;

    }

    /**
     * Navigates to the specified screen using its screen name.
     **/
    fun navigateTo(screenName: String) {
        navController.navigate(screenName) {
            launchSingleTop = true
        }
    }

    /**
     * Navigates to the specified screen using its screen name and pops up to the specified screen.
     **/
    fun navigateWithPopUp(screenName: String, popUpTo: String) {
        navController.navigate(screenName) {
            launchSingleTop = true
            popUpTo(popUpTo) {
                inclusive = true
            }
        }
    }

    fun navigateWithPopUpClearingAllStack(screenName: String) {
        navController.navigate(screenName) {
            popUpTo(0)
        }
    }

    /**
     * Navigates to the specified screen using its screen name along with arguments.
     */
    fun navigateToWithArgs(screenName: String, argsBuilder: NavOptionsBuilder.() -> Unit) {
        navController.navigate(screenName, argsBuilder)
    }

    /**
     * Navigates to the back screen.
     */
    fun navigateBack() {
        navController.popBackStack()
    }

    /**
     * Navigates to the appropriate screen based on the user's login status.
     */
    fun navigateBasedOnLoginStatus() {
//        Log.i(
//            "NavigationService",
//            "navigateBasedOnLoginStatus : ${authenticationViewModal.isUserLoggedIn()}"
//        )
//        if (authenticationViewModal.isUserLoggedIn()) {
//            navigateWithPopUp(Screens.HomeScreen.route, Screens.SplashScreen.route)
//        } else {
//            navigateWithPopUp(Screens.LoginScreen.route, Screens.SplashScreen.route);
//        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationService(intent: Intent?) {
    val navController = rememberNavController()
    val authenticationViewModal: AuthenticationViewModal = viewModel()
    NavigationService.initialize(navController, authenticationViewModal)

    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen()
        }
        composable(route = Screens.LoginScreen.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = BuildConfig.REDIRECT_URI
                action = Intent.ACTION_VIEW
            })
        ) {
            LogInScreen(intent = intent)
        }
        composable(route = Screens.HomeScreen.route) { HomeScreen() }
        composable(
            route = Screens.NotesScreen.route
        ) {
            val accountId = it.arguments?.getString("accountId") ?: ""
            val accountName = it.arguments?.getString("accountName") ?: ""
            val isAccountSelectionEnabled =
                it.arguments?.getString("isAccountSelectionEnabled")?.toBoolean() ?: false
            NotesScreen(accountName, accountId, isAccountSelectionEnabled)
        }
        composable(route = Screens.AccountDetailsScreen.route) {
            val accountId = it.arguments?.getString("accountId") ?: ""
            val accountName = it.arguments?.getString("accountName") ?: ""
            AccountDetails(accountId, accountName)
        }
        composable(route = Screens.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(route = Screens.NoteDetailsScreen.route){
            val accountId = it.arguments?.getString("accountId") ?: ""
            val accountName = it.arguments?.getString("accountName") ?: ""
            val noteId = it.arguments?.getString("noteId") ?: ""
            NoteDetailScreen(accountId, accountName,noteId)
        }
    }
}
