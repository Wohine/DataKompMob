package com.example.datakompgaming.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.datakompgaming.produkt.ProduktObject
import com.example.datakompgaming.produkt.ProdukterFire
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
            ProduktObject.produktListe.add(p1)

        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}

