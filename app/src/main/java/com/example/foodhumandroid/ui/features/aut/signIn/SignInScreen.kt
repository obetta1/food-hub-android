package com.example.foodhumandroid.ui.features.aut.signIn

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodhumandroid.R
import com.example.foodhumandroid.ui.features.widgets.SingUpWidget
import com.example.foodhumandroid.ui.features.widgets.TextInputFieldWidget
import com.example.foodhumandroid.ui.theme.Black
import com.example.foodhumandroid.ui.theme.Gray
import com.example.foodhumandroid.ui.theme.Orange
import com.example.foodhumandroid.ui.theme.PrimaryColor
import com.example.foodhumandroid.ui.theme.White

@Composable
fun SignInScreen( viewmodel: SignInViewModel = hiltViewModel()) {
    val email = viewmodel.email.collectAsStateWithLifecycle()
    val password = viewmodel.password.collectAsStateWithLifecycle()
    val  error = remember { mutableStateOf<String?>( null) }
    val loading = remember { mutableStateOf<Boolean>(false) }



    val uiState = viewmodel.uiState.collectAsState()

    when(uiState.value){
        SignInViewModel.SignInEvent.Error -> {
            error.value = "Failed to sign up"
            loading.value =  false
        }
        SignInViewModel.SignInEvent.Loading -> {
            loading.value = true
            error.value = null
        }
        SignInViewModel.SignInEvent.Success -> {
            loading.value = false
            error.value = null
        }
        else-> Unit
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.signup_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Box(modifier = Modifier.padding(16.dp)){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Sign In",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 37.sp,
                    modifier = Modifier
                        .padding(top = 93.dp, bottom = 31.dp)
                        .padding(16.dp)

                )
//                TextInputFieldWidget(value = name.value, onValueChange = {
//                    viewmodel.onNameChange(it)
//                                                                   },
//                    Modifier.fillMaxWidth(),
//                    placeholder = {Text("Enter full name",  color = Color.Gray)},
//                    label ={ Text("Full name",  color = Color.Gray) } )
//
//                Spacer(modifier = Modifier.size(20.dp))
                TextInputFieldWidget(value = email.value, onValueChange = {viewmodel.onEmailChange(it)},
                    Modifier.fillMaxWidth(),
                    placeholder = {Text("Enter E-mail",  color = Color.Gray)},
                    label ={ Text("E-mail",  color = Color.Gray) } )

                Spacer(modifier = Modifier.size(20.dp))
                TextInputFieldWidget(value = password.value, onValueChange = {viewmodel.onPasswordChange(it)},
                    Modifier.fillMaxWidth(),
                    placeholder = {Text("Password",  color = Color.Gray)},
                    label ={ Text("Password",  color = Color.Gray) },
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(R.drawable.ic_eye),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        ) })

                Button(
                    onClick = {viewmodel.onLoginPressed()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 33.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    shape = RoundedCornerShape(32.dp),
                    border = BorderStroke(1.dp, color = Color.White)
                ) {
                    Box(){
                        AnimatedContent(targetState = loading.value,
                            transitionSpec = {
                                fadeIn( animationSpec = tween(300)) + scaleIn(initialScale = .8f) togetherWith
                                        fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
                            }
                        ) {target->
                            if(target){
                                CircularProgressIndicator(
                                    color = White
                                )
                            }else{
                                Text(
                                    "SIGN UP",
                                    color = White,
                                    fontSize = 15.sp,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight(600),
                                    modifier = Modifier.padding(18.dp)
                                )
                            }

                        }
                    }


                }

                Row(verticalAlignment = Alignment.CenterVertically

                    ){
                    Text(
                        stringResource(R.string.dont_have_an_account),
                        color = Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        modifier = Modifier
                            .padding(bottom = 49.dp, top = 24.dp)
                        ,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        stringResource(R.string.signup),
                        color = PrimaryColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        modifier = Modifier
                            .padding(bottom = 49.dp, top = 24.dp),
                        textAlign = TextAlign.Center
                    )
                }
                SingUpWidget(color = Gray.copy(alpha = 0.5f), onClickFacebook = {}, onClickGoogle = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignInScreen()
}