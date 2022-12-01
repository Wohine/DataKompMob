package com.example.datakompgaming

/**
 * instansierer routes slik at brukeren kan navigere siden
 */
sealed class Routes(val route: String) {
    object test : Routes("test")
    object BestillingComp : Routes("Bestilling")
    object Chat : Routes("Chat")
    object OmOss : Routes("OmOss")
    object Kundeservice : Routes("Kundeservice")
    object Produkter : Routes("Produkter")
    object HomePage : Routes("HomePage")
    object UserPage : Routes("UserPage")
    object bruktProduktSkjema : Routes("bruktProduktSkjema")
    object Handlekurv : Routes("Handlekurv")
    object BrukteProdukter : Routes("Brukteprodukter")
    object Shipping : Routes("Shipping")
    object UserSettings : Routes("UserSettings")
}