package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.bruktProdukt.BruktProduktObject
import com.example.datakompgaming.bruktProdukt.BrukteProdukterFire
import com.google.firebase.firestore.Source



fun BrukteProdukterUthentingDB(){
    BrukteProdukterUthentingDB("Skjermkort")
    BrukteProdukterUthentingDB("Prosessorer")
    BrukteProdukterUthentingDB("Hovedkort")
}
fun BrukteProdukterUthentingDB(collectionType: String) { // 1-skjerm, 2-pross, 3-hoved
    BruktProduktObject.BruktSkjermKortListe.clear()
    BruktProduktObject.BruktProsessorerListe.clear()
    BruktProduktObject.BruktHovedKortListe.clear()

    var docRef = firestore.collection("Produkter").document("BrukteProdukter")
        .collection(collectionType)
    var source = Source.DEFAULT

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            Log.d(TAG, "${document.id} => ${document.data}")
            var p2 = BrukteProdukterFire(
                kategori = document["kategori"].toString(),
                pris = document["pris"].toString().toDouble(),
                produktNavn = document["produktNavn"].toString(),
                produsent = document["produsent"].toString(),
                tilstand = document["tilstand"].toString()
            )
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