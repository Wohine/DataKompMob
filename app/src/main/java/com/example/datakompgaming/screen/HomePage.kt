package com.example.datakompgaming.screen



import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch as launch


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
    Image(painter = painterResource(com.example.datakompgaming.R.drawable.datakomplogo),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(150.dp)
    )
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
        )
        Image(painter = imagePainter, contentDescription = "test",
            modifier = Modifier
                .height(180.dp),
            alignment = Alignment.CenterStart
        )
        Button(onClick = {},
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
            FeatProd()
            FeatProd()
            FeatProd()
        }
    }
}
@Preview
@Composable
fun ProduktSlider() {
    Row(modifier = Modifier
        .height(150.dp)
        .horizontalScroll(rememberScrollState(), enabled = true),
    ) {
        Kort(painterResource(id = R.drawable.slider1))
        Kort(painterResource(id = R.drawable.slider2))
        Kort(painterResource(id = R.drawable.slider3))
        Kort(painterResource(id = R.drawable.slider4))
        Kort(painterResource(id = R.drawable.slider5))
    }
}

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

data class test123(
    val img: String
    )

fun createItems() = listOf(
    test123("https://cdn.discordapp.com/attachments/970741785406111754/1037376850890063872/unknown.png"),
    test123("https://cdn.discordapp.com/attachments/970741785406111754/1037380583682220162/cover2.png"),
    test123("https://cdn.discordapp.com/attachments/970741785406111754/1037385940047499365/unknown.png")
    )

@Preview
@Composable
fun FeatProd() {
    Column(
        modifier = Modifier
    ) {
        Text(text = "Sample text",
            modifier = Modifier
                .absolutePadding(bottom = Dp(5f))
                .background(Color.Transparent),
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFFbd4fdb),

        )
        ProduktSlider()
    }
}







// TODO: sliding cards
// TODO: clickable images
// TODO: drepe chris