package com.example.datakompgaming.bruktProdukt


/**
 * Definerer data klasse for produktet som skal lastes opp til databasen
 * fra bruktProduktSkjema
 */
data class BruktProdukt(
    val tittel: String,
    val kategori: String,
    val produsent: String,
    val pris: String,
    val tilstand: String,
    val varebeholdning: String,
    val bildeAdresse: String
)