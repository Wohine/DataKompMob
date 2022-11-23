package com.example.datakompgaming.produkt

import com.google.firebase.firestore.Exclude

data class BrukteProdukterFire(
    val bilde: String,
    val rating: String,
    val varebeholdning: String,
    val tilstand: String,
    val pris: Double?,
    val tittel: String,
    val typeProdukt: String,
    val docNavn: String
) {

}
