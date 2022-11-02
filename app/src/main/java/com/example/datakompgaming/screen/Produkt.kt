package com.example.datakompgaming.screen

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

fun Produkter() {

    data class produkt(
        val tittel: String = "",
        val pris: Double = 0.0,
        val varebeholdning: Int = 0
    )

    var firestore: FirebaseFirestore

    firestore = FirebaseFirestore.getInstance()

    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Hovedkort")
    var source = Source.CACHE

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }



}