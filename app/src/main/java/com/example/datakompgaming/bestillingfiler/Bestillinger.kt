package com.example.datakompgaming.bestillingfiler

object Bestillinger {
    val bestilligListe = listOf<Order>(
        Order(
            1,
            "01. 01. 2001",
            "flpldfkofdidfgj ndfj",
            listOf<Produkt>(
                Produkt(1, "MSI Meg X570 Unify motherboard", "* * *", "NOK 3879", R.drawable.bilde1),
                Produkt(2, "ASUS ROG Motherboard B550-F", "* * * *", "NOK 1949", R.drawable.bilde2)
            ),
        "totalpris: 1000"),
        Order(
            2,
            "01. 01. 2002",
            "hdsajhkjshjkdshjskdhujiksd ",
            listOf<Produkt>(
                Produkt(3, "MSI Meg X57dskksdjkds ", "* * *", "NOK 3879", R.drawable.bilde1),
                Produkt(4, "ASUS Rdsdssd0-F", "* * * *", "NOK 1949", R.drawable.bilde2)
            ),
            "totalpris: 2020"
        )
    )
}