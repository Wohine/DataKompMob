package com.example.datakompgaming.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.Text
import androidx.compose.material.TextField
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import java.util.stream.Collectors.toMap

fun Produkter() {

    data class produkt(
        val tittel: String? = "",
        val pris: Double? = 0.0,
        val varebeholdning: Int? = 0,
        val bilde: String? = "",
        val rating: Int? = 0
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

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Hovedkort")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            produkt(tittel = document.getString("tittel"))
            produkt(pris = document.getDouble("pris"))
            produkt(varebeholdning = document.get("varebeholdning") as Int?)
            produkt(bilde = document.get("bilde") as String?)
            produkt(rating = document.get("rating") as Int?)
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}