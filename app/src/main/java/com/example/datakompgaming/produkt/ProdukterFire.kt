package com.example.datakompgaming.produkt

import com.google.firebase.firestore.Exclude

/**
 * data class for produkt variabler
 */
data class ProdukterFire(
    val bilde: String,
    val rating: String,
    val varebeholdning: String,
    val pris: Double,
    val tittel: String,
    val typeProdukt: String,
    val docNavn: String
) {
    /**
     * mapper dataen
     */
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "bilde" to bilde,
            "rating" to rating,
            "varebeholdning" to varebeholdning,
            "pris" to pris,
            "tittel" to tittel,
            "typeProdukt" to typeProdukt,
            "docNavn" to docNavn
        )
    }
}
