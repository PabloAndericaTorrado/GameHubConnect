package com.pabloat.GameHubConnect.ui.views

import android.text.InputFilter
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pabloat.GameHubConnect.navigation.Destinations
import com.pabloat.GameHubConnect.viewmodel.FireBaseViewModel


@Composable
fun UserForm(
    isCreatedAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, pwd -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val valido = remember(email.value,password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if (isCreatedAccount) "Crear cuenta " else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun SubmitButton(textId: String,
                 inputValido:Boolean,
                 onClic:()->Unit) {
    Button(
        onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(), shape = CircleShape,
        enabled = inputValido
    ) {
        Text(
            text = textId,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId) },
        singleLine = true,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()) {
                passwordVisibleIcon(passwordVisible)
            } else null
        }
    )
}

@Composable
fun passwordVisibleIcon(passwordVisible: MutableState<Boolean>) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility
    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun LoginScreen(navController: NavController, fireBaseViewModel: FireBaseViewModel = viewModel()) {
    val showSnackbar = remember { mutableStateOf(false) }
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    val (errorMessage, setErrorMessage) = remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginForm.value) {
                Text(text = "Inicia sesion")
                UserForm(
                    isCreatedAccount = false
                ) { email, password ->
                    Log.d("MV", "Logueado con $email y $password")
                    fireBaseViewModel.storeEmail(email)
                        Log.d("MV", "Entra en el try")
                        fireBaseViewModel.SingInWithEmailAndPassword(email, password) {
                            if (fireBaseViewModel.getStoredEmail() == "admin@admin.com") {
                                Log.d("MV", "Es ADMIN")
                                navController.navigate(Destinations.ManageScreen.route)
                            }else{
                                Log.d("MV", "No es ADMIN")
                                navController.navigate(Destinations.InitScreen.route)
                            }
                        }
                    Log.d("MV", "Aqui Chat")
                }


            } else {
                Text("crea una cuenta")
                UserForm(
                    isCreatedAccount = true
                ) { email, password ->
                    Log.d("MV", "creando cuenta con $email,$password")
                    fireBaseViewModel.createUserWithEmailAndPassword(email, password) {
                        navController.navigate(Destinations.InitScreen.route)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text1 =
                    if (showLoginForm.value) "¿No tienes cuenta?"
                    else "¿Ya tienes cuenta?"
                val text2 =
                    if(showLoginForm.value) "Registrate"
                    else "inicia Sesion"
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = Color.Cyan)
            }
        }
    }
}

@Composable
private fun MostrarSnackbar(showSnackbar: MutableState<Boolean>) {
    if (showSnackbar.value) {
        Snackbar(
            action = {
                TextButton(onClick = { showSnackbar.value = false }) {
                    Text("Cerrar")
                }
            }
        ) {
            Text("El videojuego ha sido borrado")
        }
    }
}
