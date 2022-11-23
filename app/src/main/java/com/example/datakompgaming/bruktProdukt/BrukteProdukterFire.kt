package com.example.datakompgaming.bruktProdukt

import com.google.firebase.firestore.Exclude

data class BrukteProdukterFire(
    val kategori: String,
    val pris: Double,
    val produktNavn: String,
    val produsent: String,
    val tilstand: String
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "kategori" to kategori,
            "pris" to pris,
            "produktNavn" to produktNavn,
            "produsent" to produsent,
            "tilstand" to tilstand,
        )
    }
}
