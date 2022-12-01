package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source

/**
 * henter ut produkter fra database
 */

fun ProdukterUthentingDB(){
    ProduktObject.SkjermKortListe.clear()
    ProdukterUthentingDB("Skjermkort")
    ProduktObject.ProsessorerListe.clear()
    ProdukterUthentingDB("Prosessorer")
    ProduktObject.HovedKortListe.clear()
    ProdukterUthentingDB("Hovedkort")
}
fun ProdukterUthentingDB(collectionType: String) {
    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Referanse for lokasjonen til produktene vi ønsker å hente.
     */
    var docRef = firestore.collection("Produkter").document("NyeProdukter")
                                            .collection(collectionType)
    var source = Source.DEFAULT

    /**
     * Henter ut alle dokumenter som møter referansen definert i docRef.
     */
    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var pris = 0.0

            if (document.getDouble("pris") != null)
                pris = document.getDouble("pris")!!

            /**
             * Binder de uthentede verdiene til et objekt.
             */
            var p1 = ProdukterFire(
                tittel = document["tittel"].toString(),
                pris = pris,
                varebeholdning = document["varebeholdning"].toString(),
                bilde = document["bilde"].toString(),
                rating = document["rating"].toString(),
                typeProdukt =  collectionType,
                docNavn = document["docNavn"].toString()
            )

            /**
             * Sjekker hvilken collection produktene er hentet fra, og legger
             * dem til en liste henholdsvis til resultatet.
             */
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