package com.example.datakompgaming.screen



import android.annotation.SuppressLint
import android.view.Gravity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.transition.Slide.GravityFlag
import com.example.datakompgaming.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {

    Scaffold(bottomBar = {
        printBotBar(navController = navController)
    })
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly


        ) {
            LogoBanner(title = "test")
            SaleBanner(title = "test")
            SaleBanner(title = "test")
            SaleBanner(title = "test")
            SaleBanner(title = "test")
            SaleBanner(title = "test")
            
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun LogoBanner(title: String) {
    Image(painter = painterResource(com.example.datakompgaming.R.drawable.datakomplogo), contentDescription = null)
}

@Composable
fun Kort(imagePainter: Painter) {
    Column(modifier = Modifier
        .padding(horizontal = 10.dp)
        .fillMaxHeight()
        .background(Color.Transparent),
    ) {
        Text(text = "testprodukt",
            modifier = Modifier
                .width(150.dp)
                .absolutePadding(left = 38.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Image(painter = imagePainter, contentDescription = "test",
            modifier = Modifier
                .height(180.dp),
            alignment = Alignment.CenterStart
        )
        Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .width(150.dp)
            .absolutePadding(left = 35.dp),
        ) {
            Text(text = "Kjøp")
        }
    }
}

@Composable
fun SaleBanner(title: String) {
    Row(modifier = Modifier
        .border(3.dp, Color.Black)
        .padding(10.dp)
    ) {
        Column() {
            Spacer(modifier = Modifier.height(5.dp))
            Image(painter = painterResource(com.example.datakompgaming.R.drawable.lurius), contentDescription = null)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

//denne brukes bare til development
@Preview
@Composable
fun PreHomePage() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
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
@Preview
@Composable
fun ProduktSlider() {
    Row(modifier = Modifier
        .height(250.dp)
        .horizontalScroll(rememberScrollState(), enabled = true),
    ) {
        Kort(painterResource(id = R.drawable.bilde1))
        Kort(painterResource(id = R.drawable.bilde1))
        Kort(painterResource(id = R.drawable.bilde1))
        Kort(painterResource(id = R.drawable.bilde1))
        Kort(painterResource(id = R.drawable.bilde1))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun WelcomeSlider() {
    val items = createItems()
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = items.size,
        state = pagerState,
    ) { currentPage ->
        Column() {
            Text(
                text = items[currentPage].title,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = items[currentPage].subtitle,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = items[currentPage].description,
            )
        }

    }

    val coroutineScope = rememberCoroutineScope()
    //knapp boilerplate
    Button(
        onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(page = 2)
            }
       },
    ) {
        Text(text = "Scroll to the third page")
 }
}

data class test123(
    val title: String,
    val subtitle: String,
    val description: String
    )

fun createItems() = listOf(test123(title = "Title1", subtitle = "Subtitle1", description = "Description1"),
    test123(title = "Title2", subtitle = "Subtitle1", description = "Description1"),
    test123(title = "Title3", subtitle = "Subtitle1", description = "Description1")
)
//@Preview
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
            fontSize = 16.sp,
            color = Color(0xFFbd4fdb),

        )
        ProduktSlider()
    }
}






// TODO: sliding cards
// TODO: clickable images
// TODO: drepe chris (done)