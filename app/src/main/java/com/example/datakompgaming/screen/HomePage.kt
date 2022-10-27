package com.example.datakompgaming.screen



import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
        .padding(horizontal = 19.dp)
        .fillMaxHeight()
        .background(Color.Transparent),
    ) {
        Text(text = "test test test")
        Image(painter = imagePainter, contentDescription = "test",
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.CenterStart
        )
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
//    Button(
//        onClick = {
//            coroutineScope.launch {
//                pagerState.animateScrollToPage(page = 2)
//            }
//        },
//    ) {
//        Text(text = "Scroll to the third page")
//    }
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