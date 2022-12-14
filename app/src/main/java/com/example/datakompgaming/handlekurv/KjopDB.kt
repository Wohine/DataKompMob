package com.example.datakompgaming.handlekurv

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

/**
 * funksjon som kjøper produkter og legger inn bestillingen i databasen
 * @param shippingFire
 */
fun kjopProdukterDB(shippingFire: ShippingFire) {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    // gir en tilfeldig id til et kjøp
    var kjopID = Random.nextInt(0, 100000)

    // mapper en bestilling til å passe databasen
    var dbProdukt = shippingFire.toMap()


    // path til å legge inn bestilling
    var docRef = firestore.collection("users").document(shippingFire.uid).
    collection("orders").document(kjopID.toString())


    docRef.set(dbProdukt).addOnSuccessListener {
        Log.d(ContentValues.TAG, "DocumentSnapshot successfully updated!")
    }
        .addOnFailureListener {
                e -> Log.w(ContentValues.TAG, "Error updating document", e)
        }
}
