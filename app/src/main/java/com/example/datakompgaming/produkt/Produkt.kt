package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject.produktListe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source




fun Produkter() {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Hovedkort")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var p1 = ProdukterFire(
                tittel = document["tittel"].toString(),
                pris = document.getDouble("pris"),
                varebeholdning = document["varebeholdning"].toString(),
                bilde = document["bilde"].toString(),
                rating = document["rating"].toString()
            )
            produktListe.add(p1)
            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p1.toString())
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}