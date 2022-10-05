package com.example.datakompgaming.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datakompgaming.HomePage
import com.example.datakompgaming.Routes
import com.example.datakompgaming.bestillingfiler.Bestillinger
import com.example.datakompgaming.bestillingfiler.printOrders

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Routes.BestillingComp.route){
            printOrders(Bestillinger.bestilligListe)
        }
    }
}