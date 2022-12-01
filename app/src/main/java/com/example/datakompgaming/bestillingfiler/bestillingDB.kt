package com.example.datakompgaming.bestillingfiler


import com.example.datakompgaming.produkt.firestore
import com.example.datakompgaming.user
import com.google.firebase.firestore.Source

/**
 * funksjon som henter hver bestilling fra databasen
 * legger hver bestilling i liste
 * legger hver produkt inn i liste i bestillingobjekt
 */
fun bestillingHent() {

    var source = Source.DEFAULT

    // pathen i firestore til bestillinger
    var docRef = firestore.collection("users").document(user?.uid.toString()).
    collection("orders")

    var i = 1

    docRef.get(source).addOnSuccessListener { documents ->
        // for løkke gjennom antall bestillinger som ble funnet i db
        for (document in documents) {
            var liste = document["basket"] as? MutableList<Map<String, Any>>

            var listeProdukter = mutableListOf<Produkt>()

            var dato = "dato: " + document["dato"]
            var totalPris = "totalpris: " + document["totalPris"]
            var key = "bestillingNr: " + document.id
            var id = "id: " + i++

            var objekt = Order(id, dato, key, listeProdukter, totalPris)

            // for løkke av antall produkter i hver bestilling
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