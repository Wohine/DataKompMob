package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject.produktListe
import com.example.datakompgaming.produkt.SkjermKortObject.SkjermKortListe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


fun Skjermkort() {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Skjermkort")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var p1 = SkjermKortFire(
                tittel = document["tittel"].toString(),
                pris = document.getDouble("pris"),
                varebeholdning = document["varebeholdning"].toString(),
                bilde = document["bilde"].toString(),
                rating = document["rating"].toString()
            )
            SkjermKortListe.add(p1)
            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p1.toString())
        }
    }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}