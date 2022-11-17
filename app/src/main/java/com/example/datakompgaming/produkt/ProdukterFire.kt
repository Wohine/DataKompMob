package com.example.datakompgaming.produkt

import com.google.firebase.firestore.Exclude

data class ProdukterFire(
    val bilde: String,
    val rating: String,
    val varebeholdning: String,
    val pris: Double?,
    val tittel: String,
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "bilde" to bilde,
            "varebeholdning" to varebeholdning,
            "rating" to rating,
            "pris" to pris,
            "tittel" to tittel,
        )
    }
}
