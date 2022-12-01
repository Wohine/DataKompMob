package com.example.datakompgaming.handlekurv


import android.util.Log
import com.example.datakompgaming.produkt.ProdukterFire
import com.example.datakompgaming.produkt.firestore
import android.content.ContentValues.TAG
import com.example.datakompgaming.produkt.ProdukterUthentingDB

/**
 * funksjon som oppdaterer varebeholdning til produkter man kjøper
 * @param produkterFire
 */
fun produktOppdateringDB(produkterFire: ProdukterFire) {

    // gammel beholdning
    var nyVareBeholdning = produkterFire.varebeholdning.toInt()

    // path
    var docRef = firestore.collection("Produkter").document("NyeProdukter").
                                    collection(produkterFire.typeProdukt).document(produkterFire.docNavn)

    // setter varebeholdning til (varebeholdning minus 1)
    docRef.update("varebeholdning", nyVareBeholdning-1)
        .addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot successfully updated!")
        }
        .addOnFailureListener {
            e -> Log.w(TAG, "Error updating document", e)
        }

    // henter ut produkter på nytt med oppdatert varer
    ProdukterUthentingDB()
}
