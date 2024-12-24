package com.niojar.navigationguide.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.niojar.navigationguide.DetailScreen
import com.niojar.navigationguide.HomeScreen
import com.niojar.navigationguide.LoginScreen
import com.niojar.navigationguide.SettingsScreen
import com.niojar.navigationguide.core.navigation.type.createNavType
import kotlin.reflect.typeOf


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { LoginScreen { navController.navigate(Home) } }

        composable<Home> { HomeScreen { name -> navController.navigate(Detail(name = name)) } }

        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailScreen(name = detail.name, navigateBack = {
                navController.navigate(Login) {
                    popUpTo<Login> {
                        inclusive = true
                    }
                }
                // Options
                // navController.navigateUp()
                // navController.popBackStack()
            }, navigateToSettings = { navController.navigate(Settings(it)) })
        }

        composable<Settings>(typeMap = mapOf(typeOf<SettingsInfo>() to createNavType<SettingsInfo>())) { backStackEntry ->
            val settings: Settings = backStackEntry.toRoute()
            SettingsScreen(settings.info)
        }
    }
}
