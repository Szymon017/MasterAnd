package com.example.masterand2

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object Game: Screen(route = "game_screen")
    object Profile: Screen(route = "profile_screen")

}