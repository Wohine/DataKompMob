package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject.produktListe
import com.example.datakompgaming.produkt.ProsessorerObject.ProsessorerListe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


fun Prosessorer() {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var docRef = firestore.collection("Produkter").document("NyeProdukter").collection("Prosessorer")
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var p1 = ProsessorerFire(
                tittel = document["tittel"].toString(),
                pris = document.getDouble("pris"),
                varebeholdning = document["varebeholdning"].toString(),
                bilde = document["bilde"].toString(),
                rating = document["rating"].toString()
            )
            ProsessorerListe.add(p1)
            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p1.toString())
        }
    }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}