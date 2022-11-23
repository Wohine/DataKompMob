package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
fun ProdukterUthentingDB(){
    ProdukterUthentingDB("Skjermkort")
    ProdukterUthentingDB("Prosessorer")
    ProdukterUthentingDB("Hovedkort")
}
fun ProdukterUthentingDB(collectionType: String) { // 1-skjerm, 2-pross, 3-hoved
    ProduktObject.SkjermKortListe.clear()
    ProduktObject.ProsessorerListe.clear()
    ProduktObject.HovedKortListe.clear()

    var docRef = firestore.collection("Produkter").document("NyeProdukter")
                                            .collection(collectionType)
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var pris = 0.0

            if (document.getDouble("pris") != null)
                pris = document.getDouble("pris")!!

            var p1 = ProdukterFire(
                tittel = document["tittel"].toString(),
                pris = pris,
                varebeholdning = document["varebeholdning"].toString(),
                bilde = document["bilde"].toString(),
                rating = document["rating"].toString(),
                typeProdukt =  collectionType,
                docNavn = document["docNavn"].toString()
            )
            if(collectionType.equals("Skjermkort"))
                ProduktObject.SkjermKortListe.add(p1)
            else if(collectionType.equals("Prosessorer"))
                ProduktObject.ProsessorerListe.add(p1)
            else
                ProduktObject.HovedKortListe.add(p1)

            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p1.toString())
        }
    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents: ", exception)
    }
}