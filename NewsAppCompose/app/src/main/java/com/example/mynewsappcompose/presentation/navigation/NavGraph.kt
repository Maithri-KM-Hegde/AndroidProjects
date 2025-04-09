package com.example.mynewsappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mynewsappcompose.presentation.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoute.Home.path) {
        addHomeScreen(navController, this)
    }
}

fun addHomeScreen(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {
        HomeScreen(
            navController = navController
        )
    }
}
