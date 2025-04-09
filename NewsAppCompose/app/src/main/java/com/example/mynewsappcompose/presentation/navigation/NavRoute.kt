package com.example.mynewsappcompose.presentation.navigation

sealed class NavRoute(val path: String) {
    object Home : NavRoute("home")

    // build and setup route format (in navigation graph) -> path/{arg}
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }

    // build navigation path (for screen navigation) -> path/arg
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}