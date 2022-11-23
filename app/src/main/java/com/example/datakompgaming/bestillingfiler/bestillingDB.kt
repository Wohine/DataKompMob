package com.example.datakompgaming.bestillingfiler

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.example.datakompgaming.bruktProdukt.BruktProduktObject
import com.example.datakompgaming.bruktProdukt.BrukteProdukterFire
import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.user
import com.google.firebase.firestore.Source

fun bestillingHent() {

    var source = Source.DEFAULT
    var docRef = firestore.collection("users").document(user?.uid.toString()).
    collection("orders")

    var i = 1

    docRef.get(source).addOnSuccessListener { documents ->
        for (document in documents) {
            var liste = document["basket"] as? MutableList<Map<String, Any>>

            var listeProdukter = mutableListOf<Produkt>()

            var dato = "dato: " + document["dato"]
            var totalPris = "totalpris: " + document["totalPris"]
            var key = "bestillingNr: " + document.id
            var id = "id: " + i++

            var objekt = Order(id, dato, key, listeProdukter, totalPris)

            if (liste != null) {
                for (item in liste){
                    var pris =  (item.get("pris") as Double).toString() + "kr"
                    var tittel = item.get("tittel").toString()
                    var bilde = item.get("bilde").toString()
                    objekt.liste.add(Produkt(tittel, pris, bilde))
                }
            }
            Bestillinger.bestilligListe.add(objekt)
        }
    }
}