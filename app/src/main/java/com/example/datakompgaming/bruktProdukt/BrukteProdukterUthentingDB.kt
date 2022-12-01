package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.bruktProdukt.BruktProduktObject
import com.example.datakompgaming.bruktProdukt.BrukteProdukterFire
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


/**
 * mer generell funksjon som kaller på databaseuthening for hver type datautstyr som selges
 */
fun BrukteProdukterUthentingDB(){
    BruktProduktObject.BruktSkjermKortListe.clear()
    BruktProduktObject.BruktProsessorerListe.clear()
    BruktProduktObject.BruktHovedKortListe.clear()
    BrukteProdukterUthentingDB("Skjermkort")
    BrukteProdukterUthentingDB("Prosessorer")
    BrukteProdukterUthentingDB("Hovedkort")
}

/**
 * henter ut et type produkt fra databasen
 */
fun BrukteProdukterUthentingDB(collectionType: String) {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // path
    var docRef = firestore.collection("Produkter").document("BrukteProdukter")
        .collection(collectionType)
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        // for løkke av antall produkter
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")

            // lagrer info om et produkt i et objekt
            var p2 = BrukteProdukterFire(
                kategori = document["kategori"].toString(),
                pris = document["pris"].toString().toDouble(),
                tittel = document["tittel"].toString(),
                produsent = document["produsent"].toString(),
                tilstand = document["tilstand"].toString(),
                bilde = document["bildeAdresse"].toString()
            )
            // if tester hvilke type produkt det er
            if(collectionType.equals("Skjermkort"))
                BruktProduktObject.BruktSkjermKortListe.add(p2)
            else if(collectionType.equals("Prosessorer"))
                BruktProduktObject.BruktProsessorerListe.add(p2)
            else
                BruktProduktObject.BruktHovedKortListe.add(p2)

            Log.d(TAG, document["tittel"].toString())
            Log.d(TAG, p2.toString())
        }
    }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}