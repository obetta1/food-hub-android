package com.example.foodhumandroid.ui.features.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodhumandroid.R


@Composable
fun SingUpWidget(
    color: Color,
    onClickFacebook: () -> Unit, onClickGoogle: () -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(bottom = 18.dp)) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = color
            )
            Text(
                "Sign Up", color = color, fontSize = 16.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(5.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = color
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SocialButtons(
                onClick = onClickFacebook,
                text = stringResource(R.string.facebook),
                painter = painterResource(R.drawable.ic_facebook),
            )
            SocialButtons(
                onClick = onClickGoogle,
                text = stringResource(R.string.google),
                painter = painterResource(R.drawable.ic_google),
            )

        }
    }

}

@Composable
fun SocialButtons(text: String, painter: Painter, onClick: () -> Unit) {
    Button(
        onClick = onClick,
//        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.8f)),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, color = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painter,
                contentDescription = null,
                Modifier
                    .height(28.dp)
                    .width(28.dp)
            )
            Text(
                text,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}