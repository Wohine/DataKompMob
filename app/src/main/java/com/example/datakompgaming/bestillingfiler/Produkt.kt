package com.example.datakompgaming.bestillingfiler

data class Produkt(
    val id: Int,
    val description: String,
    val stjerner: String,
    val pris: String,
    val bildeId: Int = 0
)
