package com.example.datakompgaming.produkt

import android.content.ContentValues.TAG
import android.util.Log
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
            var p2 = ProdukterFire(
                tittel = document["tittel"].toString(),
                pris = document.getDouble("pris"),
                bilde = document["bilde"].toString(),
                tilstand = document["tilstand"].toString(),
                typeProdukt =  collectionType,
                docNavn = document["docNavn"].toString()
            )
            if(collectionType.equals("Skjermkort"))
                ProduktObject.SkjermKortListe.add(p2)
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