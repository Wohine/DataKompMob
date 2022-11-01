package com.example.datakompgaming.screen

import android.widget.Toast
import androidx.navigation.NavController
import com.example.datakompgaming.MainActivity
import com.example.datakompgaming.mainActivity
import com.google.firebase.firestore.FirebaseFirestore

fun Produkter() {

    var firestore: FirebaseFirestore
    firestore = FirebaseFirestore.getInstance()
    firestore.collection("users")
        .get()
        .addOnSuccessListener {
            Toast.makeText(mainActivity, "Your first name is ${it.documents.get(0).data?.get("first_name") }  and last name is ${it.documents.get(0).data?.get("last_name") }", Toast.LENGTH_SHORT).show()
    }
    .addOnFailureListener{
        it.printStackTrace()
        Toast.makeText(mainActivity, "Failed", Toast.LENGTH_SHORT).show()
    }

}