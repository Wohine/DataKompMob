package com.example.datakompgaming

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.datakompgaming.handlekurv.produktOppdateringDB
import com.example.datakompgaming.produkt.*
import com.example.datakompgaming.screen.LogoBanner
import com.example.datakompgaming.screen.ScreenMain
import com.example.datakompgaming.ui.theme.DataKompGamingTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

var mainActivity: MainActivity? = null
var user: FirebaseUser? = null

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = this
        ProdukterUthentingDB()
        BrukteProdukterUthentingDB()
        setContent {
            DataKompGamingTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    login()
                }
            }
        }

    }

    @ExperimentalMaterial3Api
    @Composable
    fun login(
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(all = 2.dp)) {
                LogoBanner(title = "test")
            }
            Row(modifier = Modifier.padding(all = 2.dp)) {
                Button(
                    onClick = {
                        signIn()
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Logg inn eller registrer ny bruker")
                }
            }
        }
    }

    @ExperimentalMaterial3Api
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.signInResult(res)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )
        val signinIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signinIntent)
    }



    private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            user = FirebaseAuth.getInstance().currentUser
            setContent {
                DefaultPreview()
            }
        } else {
            Log.e("FirebaseAuth", "Error logging in " + response?.error?.errorCode)
        }
    }

    fun logOut(){
        FirebaseAuth.getInstance().signOut();
        setContent {
            login()
        }
    }

}



@ExperimentalMaterial3Api
@Composable
fun DefaultPreview() {
    MaterialTheme {
        ScreenMain()
    }
}
@ExperimentalMaterial3Api
@Composable
fun LogoutPreview() {
    MaterialTheme {
        mainActivity?.login()
    }
}

