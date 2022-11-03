package com.example.datakompgaming.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.datakompgaming.bestillingfiler.Order
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import java.util.stream.Collectors.toMap

val produktListe = mutableListOf<ProdukterFire>()


fun Produkter() {
    /*
    data class produkt(
        val tittel: String?,
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
     */

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    
    
    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Hovedkort")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            var p1 = ProdukterFire(
                tittel = document.getString("tittel")?: "null",
                pris = document.getDouble("pris"),
                varebeholdning = document.get("varebeholdning") as Int?,
                bilde = document.get("bilde") as String?,
                rating = document.get("rating") as Int?
            )
            produktListe.add(p1)
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}
/*
        produkt(tittel = document.getString("tittel"))
        produkt(pris = document.getDouble("pris"))
        produkt(varebeholdning = document.get("varebeholdning") as Int?)
        produkt(bilde = document.get("bilde") as String?)
        produkt(rating = document.get("rating") as Int?)
 */


@Composable
fun qwert(navController: NavController){
    Produkter()
    for (produkt in produktListe){
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = produkt.tittel)
    }
}