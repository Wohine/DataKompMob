package com.example.datakompgaming.screen

import com.google.firebase.firestore.Exclude

data class ProdukterFire(
    val tittel: String,
    val pris: Double?,
    val varebeholdning: Int?,
    val bilde: String?,
    val rating: Int?
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "tittel" to tittel,
            "pris" to pris,
            "varebeholdning" to varebeholdning,
            "bilde" to bilde,
            "rating" to rating
        )
    }
}
