package com.example.datakompgaming.bruktProdukt

import com.google.firebase.firestore.Exclude

data class BrukteProdukterFire(
    val kategori: String,
    val pris: Double,
    val produktNavn: String,
    val produsent: String,
    val tilstand: String,
    val bilde: String,
) {

}
