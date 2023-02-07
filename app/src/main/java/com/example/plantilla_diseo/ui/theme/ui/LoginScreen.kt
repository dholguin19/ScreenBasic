package com.example.plantilla_diseo.ui.theme.ui

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.plantilla_diseo.R
import kotlinx.coroutines.launch


//vista
@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    //empieza desde el centro con BOX
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //poner al centro
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoanding.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        //Alienar al centro modifier
        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(12.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(30.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch { viewModel.onLoginSelected() }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF0BDCF5),
            disabledBackgroundColor = Color(0xFF0B2E33),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Sign in")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        modifier = modifier.clickable { },
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1AE5FF)
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChangedPackages: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChangedPackages(it) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30),
        placeholder = {
            Text(
                text = "Password",
                color = Color(0xFFFFFFFF),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF03A9F4),
            backgroundColor = Color(0xFF8E76B9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

//texto del email
@Composable
fun EmailField(email: String, onTextFieldChangedPackages: (String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { onTextFieldChangedPackages(it) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30),
        placeholder = {
            Text(
                text = "Username",
                color = Color(0xFFF8F6F6),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF03A9F4),
            backgroundColor = Color(0xFF8E76B9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

//Cargar la imagen
@Composable
fun HeaderImage(modifier: Modifier) {
    /*Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Header",
        modifier = modifier,
    )*/

    AsyncImage(
        model = R.drawable.login,
        contentDescription = null,
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp)),
    )
}
