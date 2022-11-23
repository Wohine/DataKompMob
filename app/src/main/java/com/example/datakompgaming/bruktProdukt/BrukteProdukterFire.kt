package com.example.datakompgaming.bruktProdukt

import com.google.firebase.firestore.Exclude

data class BrukteProdukterFire(
    val kategori: String,
    val pris: Double,
    val tittel: String,
    val produsent: String,
    val tilstand: String,
    val bilde: String
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "kategori" to kategori,
            "pris" to pris,
            "tittel" to tittel,
            "produsent" to produsent,
            "tilstand" to tilstand,
            "bilde" to bilde
        )
    }
}
