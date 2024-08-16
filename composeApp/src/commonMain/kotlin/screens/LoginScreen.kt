package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.visibility
import kmp.composeapp.generated.resources.visibility_off
import models.request.login.LoginRequest
import org.jetbrains.compose.resources.painterResource
import utils.Response
import view_models.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val detailState by loginViewModel.loginState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    if (detailState.error != null){
        Text(detailState.error.orEmpty())
    }
    if (detailState.loginResponse != null){
        Text(detailState.loginResponse!!.message)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Login", style = TextStyle(
                    color = Color.Black,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W500,
                    fontSize = 25.sp
                ), modifier = Modifier.padding(top = 18.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.W400, color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.W400, color = Color.Black
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (passwordVisible) Res.drawable.visibility else Res.drawable.visibility_off
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.padding(end = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (username.isEmpty() || password.isEmpty()) return@Button
                    else {
                        val loginRequest = LoginRequest(username, password)
                        loginViewModel.login(loginRequest)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red, contentColor = Color.White
                )
            ) {
                Text("Login", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500))
            }
        }

        // Loader Overlay
        if (detailState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White, // Color of the loader
                )
            }
        }
    }
}

