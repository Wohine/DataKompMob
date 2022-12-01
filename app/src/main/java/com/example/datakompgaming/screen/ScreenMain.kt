package com.example.datakompgaming.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datakompgaming.Routes
import com.example.datakompgaming.bestillingfiler.Bestillinger
import com.example.datakompgaming.bestillingfiler.printOrders
import com.example.datakompgaming.bruktProdukt.BruktProduktObject
import com.example.datakompgaming.handlekurv.printShippingSkjema
import com.example.datakompgaming.produkt.ProduktObject
import com.example.datakompgaming.produkt.*
import com.example.datakompgaming.screen.chat.ChatApp

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
        composable(Routes.Chat.route){
            ChatApp(navController)
        }
        composable(Routes.OmOss.route){
            OmOss(navController)
        }
        composable(Routes.Kundeservice.route){
            Kundeservice(navController)
        }
        composable(Routes.Produkter.route){
           Produkter(navController, ProduktObject.HovedKortListe, ProduktObject.ProsessorerListe, ProduktObject.SkjermKortListe)
        }
        composable(Routes.HomePage.route){
            HomePage(navController)
        }
        composable(Routes.UserPage.route){
            UserPage(navController)
        }
        composable(Routes.bruktProduktSkjema.route){
            bruktProduktSkjema(navController)
        }
        composable(Routes.UserSettings.route){
            UserSettings(navController)
        }
        composable(Routes.Handlekurv.route){
            printHandlekurv(navController)
        }
        composable(Routes.BrukteProdukter.route){
            BruktMarked(navController)
        }
        composable(Routes.Shipping.route){
            printShippingSkjema(navController)
        }
    }
}