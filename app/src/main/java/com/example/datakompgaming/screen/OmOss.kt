package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.datakompgaming.R
import com.example.datakompgaming.ui.theme.DataKompGamingTheme

/**
 * omOss siden som forklarer litt om hvem vi i Datakomp er og våres visjon
 */
@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OmOss(navController: NavController) {
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            Scaffold(
                bottomBar = {
                    printBotBarIcon(navController = navController, 3)
                },
                topBar = {
                    printTopBarIcon(navController = navController)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState(), enabled = true)
                    //.background(Color.White)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly


                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    Title("Om Oss")
                    /**
                     * tar in tekst og bilde og viser det på siden
                     */
                    InfoCard(
                        "Hvem er vi?",
                        "Vi er fem studenter som studerer IT og informasjonssytemer på USN Campus Bø.",
                        "Gjennom vår interesse for teknologi og duppeditter har vi alle kjent på risikoen ved å kjøpe elektroniske produkter brukt.",
                        "https://www.usn.no/getfile.php/13520469-1525427372/usn.no/om_USN/Logo%20og%20grafiske%20elementer/USN_logotype.png"
                    )
                    InfoCard(
                        "Mål",
                        "Det kastes enorme mengder e-avfall rundt om i verden. Dette er ofte produkter som er i god stand.",
                        "Vi tror at ved å tilby et bruktmarked for elektroniske varer hvor produktene er sjekket for feil av fagfolk, kan vi redusere mistilliten en har til brukte produkter, samt e-avfallet som genereres av det Norske folk.",
                        "https://techmonitor.ai/wp-content/uploads/sites/4/2020/07/2014waste.jpg"
                    )
                    InfoCard(
                        "Pris",
                        "Verden fikk kjenne på silisiumsmangelen gjennom 2021. Å få tak i produkter med mikroprosessorer var både dyrt og sjeldent.",
                        "Ved å kjøpe brukte produkter av oss unngår du å betale ekstra for manglende tilbud, og du får et produkt du kan stole på.",
                        "https://p0.pikist.com/photos/779/577/cpu-processor-macro-pen-pin-computer-electronics-data-processing-chip.jpg"
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

}

/**
 * infokort som tar in omOss informasjon og viser det i omOss elementet
 */
@Composable
fun InfoCard(title: String, line1: String, line2: String, image: String) {
    Row(modifier = Modifier
        .border(3.dp, Color.Black)
        .padding(10.dp)
    ) {
        Column() {
            Text(title, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(line1)
            Spacer(modifier = Modifier.height(5.dp))
            Text(line2)
            Spacer(modifier = Modifier.height(5.dp))
            AsyncImage(model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}