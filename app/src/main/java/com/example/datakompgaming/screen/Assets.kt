package com.example.datakompgaming.screen

import android.annotation.SuppressLint
import android.app.ActivityManager.TaskDescription
import androidx.compose.foundation.Image
import com.example.datakompgaming.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@ExperimentalMaterial3Api
@Composable
fun ImageCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,

        ),
        shape = MaterialTheme.shapes.large
    )
    {
        Image(
            painter = painterResource(R.drawable.datakomplogo),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
        )
        Column(
            modifier = Modifier.padding((16.dp))
        )
        {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 8.dp,
                mainAxisSize = SizeMode.Wrap
            )
            {
                AssistChip(
                    onClick = { },
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    },
                    label =
                    {
                        Text(text = "Legg til i handlekurv")
                    }
                )

                AssistChip(
                    onClick = { },
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = null
                        )
                    },
                    label =
                    {
                        Text(text = "Legg til i handlekurv")
                    }
                )


            }

        }

    }
}







/*
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

 */
