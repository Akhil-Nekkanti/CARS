package com.example.cars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.cars.repository.OAuthRepository
import com.example.cars.util.NetworkModule
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cars.ui.theme.CARSTheme
import android.content.Intent
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException

class MainActivity : ComponentActivity() {
    private lateinit var credentialManager: CredentialManager

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        handleSignInResult(result.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CARSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        lifecycleScope.launch {
            val repository = OAuthRepository(NetworkModule.oAuthService)
            val credentials = repository.getOAuthCredentials()
            // Use the credentials as needed, e.g., for signing in with Google
        }

        credentialManager = CredentialManager.create(this)

        val signInIntent = Intent(this, CredentialManager::class.java)
        signInLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(data: Intent?) {
        try {
            val response = data?.getParcelableExtra<GetCredentialResponse>(CredentialManager.EXTRA_CREDENTIAL_RESPONSE)
            val credential = response?.credential
            // Signed in successfully, show authenticated UI.
            updateUI(credential)
        } catch (e: GetCredentialException) {
            // Handle the error
            updateUI(null)
        }
    }

    private fun updateUI(credential: Any?) {
        if (credential != null) {
            // Use credential information to update your UI
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CARSTheme {
        Greeting("Android")
    }
}