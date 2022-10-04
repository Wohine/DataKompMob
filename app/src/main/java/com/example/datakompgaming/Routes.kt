package com.example.datakompgaming

sealed class Routes(val route: String) {
    object Login : Routes("Login")
}