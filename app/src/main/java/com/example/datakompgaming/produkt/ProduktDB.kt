package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

fun ProdukterUthentingDB(docRef : CollectionReference, i : Int) { // 1-skjerm, 2-pross, 3-hoved


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
            if(i == 1)
                ProduktObject.SkjermKortListe.add(p1)
            else if(i == 2)
                ProduktObject.ProsessorerListe.add(p1)
            else if(i == 3)
                ProduktObject.HovedKortListe.add(p1)

            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p1.toString())
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}