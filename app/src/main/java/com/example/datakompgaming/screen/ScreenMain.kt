package com.example.datakompgaming.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datakompgaming.MainActivity
import com.example.datakompgaming.Routes
import com.example.datakompgaming.bestillingfiler.Bestillinger
import com.example.datakompgaming.bestillingfiler.printOrders

@ExperimentalMaterial3Api
@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    // lag ny composable, lag ny route i Route, send med navcontroller som
    // parameter
    // lag en scaffold med botbar se linje 28-30 for eksempel

    NavHost(navController = navController, startDestination = Routes.HomePage.route) {
        composable(Routes.BestillingComp.route){
            printOrders(Bestillinger.bestilligListe, navController)
        }
        composable(Routes.Test2Chris.route){
            Test2Chris(navController)
        }

        /*
        composable(Routes.test.route){
            Test(navController)
        }

         */

        composable(Routes.Chat.route){
            PreviewConversation(navController)
        }
        composable(Routes.OmOss.route){
            OmOss(navController)
        }
        composable(Routes.Kundeservice.route){
            Kundeservice(navController)
        }
        composable(Routes.Produkter.route){
            Produkter(navController)
        }
        composable(Routes.HomePage.route){
            HomePage(navController)
        }

    }
}