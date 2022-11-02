package com.example.datakompgaming.screen



import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {

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