package com.example.datakompgaming

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.datakompgaming.screen.ScreenMain
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

var mainActivity: MainActivity? = null

class MainActivity : ComponentActivity() {

    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = this
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain()
                }
            }
        }

    }
    @Composable
    fun login(
    ) {
        Column {
            Row(modifier = Modifier.padding(all = 2.dp)) {
                Button(
                    onClick = {
                        signIn()
                    }
                ) {
                    Text(text = "Logg inn")    
                }
            }
        }
    }

        private fun signIn() {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            val signinIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signinIntent)
        }

        private val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { res ->
            this.signInResult(res)
        }

        fun logOut(){
            FirebaseAuth.getInstance().signOut();
            setContent {
                LogoutPreview()
            }
        }


        private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
            val response = result.idpResponse
            if (result.resultCode == RESULT_OK) {
                user = FirebaseAuth.getInstance().currentUser
                setContent {
                    DefaultPreview()
                }
            } else {
                Log.e("Login.kt", "Error logging in " + response?.error?.errorCode)
            }
        }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        ScreenMain()
    }
}

@Composable
fun LogoutPreview() {
    MaterialTheme {
        mainActivity?.login()
    }
}
