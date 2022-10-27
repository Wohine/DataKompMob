package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import com.example.datakompgaming.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController





@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Assets(navController: NavController) {
    Scaffold(bottomBar = {
        printBotBar(navController = navController)
    })
    {
        Card {
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
                .absolutePadding(right = Dp(35f))
                .clickable { println("Clicked") },
            shape
        }

        Row()
        {

        }

        Column()
        {

        }

        Text(text = )

        Button(onClick = { /*TODO*/ }) {

        }


    }
}
