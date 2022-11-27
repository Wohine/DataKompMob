package com.example.datakompgaming.handlekurv

import android.content.ContentValues
import android.util.Log
import com.example.datakompgaming.produkt.ProduktObject
import com.example.datakompgaming.produkt.ProdukterFire
import com.example.datakompgaming.produkt.firestore
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import android.content.ContentValues.TAG
import com.example.datakompgaming.produkt.ProdukterUthentingDB


fun produktOppdateringDB(produkterFire: ProdukterFire) { // 1-skjerm, 2-pross, 3-hoved
    var nyVareBeholdning = produkterFire.varebeholdning.toInt()

    var docRef = firestore.collection("Produkter").document("NyeProdukter").
                                    collection(produkterFire.typeProdukt).document(produkterFire.docNavn)

    docRef.update("varebeholdning", nyVareBeholdning-1)
        .addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot successfully updated!")
        }
        .addOnFailureListener {
            e -> Log.w(TAG, "Error updating document", e)
        }
    ProdukterUthentingDB()
}