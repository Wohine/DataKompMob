package com.example.datakompgaming.screen



import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.datakompgaming.R
import com.example.datakompgaming.produkt.ProduktObject

import com.example.datakompgaming.produkt.ProdukterFire
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch as launch

/**
 *Homepage henter inn navcontroller slik at man kan skifte pages.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {
    //våres egen theme som definerer styling
    DataKompGamingTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        ) {
            /**
             * scaffold for å ha en permanent topbar og botbar
             */
            Scaffold(
                bottomBar = {
                    printBotBarIcon(navController = navController, 0)
                },
                topBar = {
                    printTopBarIcon(navController = navController)
                }
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState(), enabled = true),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /**
                     * plasserer de ulike Composable elementene i Homepage
                     */
                    LogoBanner(title = "test")
                    WelcomeSlider()
                    FeatProd("Gode Tilbud")
                    FeatProd("Ukens Produkter")
                    FeatProd("Våre Beste Produkter")
                }
            }
        }
    }
}

@Composable
fun LogoBanner(title: String) {
    Spacer(modifier = Modifier.height(15.dp))
    Image(painter = painterResource(R.drawable.datakomplogo),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(150.dp)
    )
}

/**
 * denne ble bare brukt under utviklingen og er en preview av hjemmesiden uten inputparametere.
 */
@Preview
@Composable
fun PreHomePage() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState(),enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoBanner(title = "test")
            WelcomeSlider()
            FeatProd("Gode Tilbud")
            FeatProd("Ukens Produkter")
            FeatProd("Våre Beste Produkter")
        }
    }
}

/**
 * henter ut produkter og plaserer dem i en produkt slider
 */
@Composable
fun ProduktSlider(tittel: String, farge: Color, produktListe: MutableList<ProdukterFire>,) {
    Row(modifier = Modifier
        .height(250.dp)
        .horizontalScroll(rememberScrollState(), enabled = true),
    ) {
        for (produkt in produktListe){
            ProdukterKort(
                produkt,
                farge
            )
        }
    }
}

/**
 * En pager som viser våres tilbud eller logo
 */
@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun WelcomeSlider() {
    val items = createItems()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            delay(4000)
            with(pagerState) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

                animateScrollToPage(
                    page = target,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }
    /**
     * pager fra accompanist
     */
    HorizontalPager(
        count = items.size,
        state = pagerState,
    ) { currentPage ->
        Column() {
            AsyncImage(model = items[currentPage].img, contentDescription = null,contentScale = ContentScale.Fit,modifier = Modifier
                .fillMaxSize())
        }

    }

}


//data for welcome banner
data class imgString(
    val img: String
    )

/**
 * liste med bilde adresser som blir referert fra firebase databasem
 */
fun createItems() = listOf(
    imgString("https://firebasestorage.googleapis.com/v0/b/datakompkotlin.appspot.com/o/images%2Fkomp1.png?alt=media&token=49a528f0-b8c0-4e1a-bd86-ccc40b3573ca"),
    imgString("https://firebasestorage.googleapis.com/v0/b/datakompkotlin.appspot.com/o/images%2Fkomp2.png?alt=media&token=0cf64192-e9a5-484d-bbb0-d8f74240ff01"),
    imgString("https://firebasestorage.googleapis.com/v0/b/datakompkotlin.appspot.com/o/images%2Fkomp3.png?alt=media&token=ffcc8d7a-c119-4260-957d-96de9b42443f")
    )

/**
 * selve Composable elementet som viser produktene
 */
@Composable
fun FeatProd(title: String) {
    Column(
        modifier = Modifier
    ) {
        Text(text = title,
            modifier = Modifier
                .absolutePadding(bottom = Dp(5f))
                .background(Color.Transparent),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color(0xFF0888c4),

        )
        /**
         * produkt slider med inputparametere
         */
        ProduktSlider("Hovedkort",Color(0xFF0888c4), ProduktObject.HovedKortListe)
    }
}




