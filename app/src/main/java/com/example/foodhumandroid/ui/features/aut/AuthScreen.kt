package com.example.foodhumandroid.ui.features.aut

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodhumandroid.R
import com.example.foodhumandroid.ui.theme.Orange
import com.example.foodhumandroid.ui.theme.White
import com.example.foodhumandroid.ui.theme.textBlack
import okhttp3.internal.wait

@Composable
fun AuthScreen(){
    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }
    val brush = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = imageSize.value.height.toFloat()/3,
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Image(painter = painterResource(id = R.drawable.auth_bg), contentDescription = null,
            modifier = Modifier
                .onGloballyPositioned {
                    imageSize.value = it.size
                }
                .alpha(0.6f)
        )
       Box(modifier = Modifier
           .matchParentSize()
           .background(brush = brush))
        Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp) ) {
            Text(stringResource(R.string.skip),
                color = Color.Black,

            )
        }

        Column(modifier = Modifier
            .padding(top = 100.dp)
            .padding(16.dp)) {
            Text(
                "Welcome to", color = Color.Black,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "FoodHub", color = Orange,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "Your favourite foods delivered fast at your door.",
                color = textBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(modifier = Modifier
            .align(Alignment.BottomCenter)) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {},
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(32.dp),
                    border = BorderStroke(1.dp, color = Color.White)
                ) {
                    Row {
                        Image(painter = painterResource(R.drawable))
                    }
                    Text(
                        stringResource(R.string.facebook),
                        color = White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
                Button(onClick = {},
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(32.dp),
                    border = BorderStroke(1.dp, color = Color.White)
                ) {
                    Text(
                        stringResource(R.string.google),
                        color = White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,

                    )
                }
            }
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, color = Color.White)
            ) {
                Text(
                stringResource(R.string.start_with_email_or_phone),
                color = White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )

            }
//
            Text(
                stringResource(R.string.already_have_an_account),
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(bottom = 28.dp)
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthScreen()
}