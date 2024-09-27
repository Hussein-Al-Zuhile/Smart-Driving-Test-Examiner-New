package com.tatweer.smartdrivingtest.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.AppColors
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.QuarterDefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.SmartDrivingTestExaminerNewTheme
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultSp

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

}

@Composable
fun LoginScreenUI(modifier: Modifier = Modifier) {
    Box(modifier) {
        Image(
            painterResource(R.drawable.bg_login_screen),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(DoubleDefaultDp)
                    .weight(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.logo_smart_driving_test_red),
                    contentDescription = "Logo",
                    Modifier
                        .fillMaxWidth(0.8f)
                )
                Spacer(Modifier.height(DoubleDefaultDp))
                Text(
                    stringResource(R.string.sign_in_here),
                    fontSize = ThreeQuarteredDoubleDefaultSp,
                    color = AppColors.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    stringResource(R.string.text_please_enter_your_military_id_password),
                    color = AppColors.Gray,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.height(HalfDefaultDp))
                var militaryID by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    militaryID, onValueChange = { militaryID = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(),
                    placeholder = {
                        Text(
                            stringResource(R.string.label_military_id),
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = ThreeQuarteredDoubleDefaultSp
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                )
                Spacer(Modifier.height(DefaultDp))
                var password by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    password, onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(),
                    placeholder = {
                        Text(
                            stringResource(R.string.label_password),
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = ThreeQuarteredDoubleDefaultSp
                        )
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                )
                Spacer(Modifier.height(DoubleDefaultDp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = stringResource(R.string.label_sign_in).uppercase(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = ThreeQuarteredDoubleDefaultSp
                    )
                }
                Spacer(Modifier.height(DefaultDp))
                Text(
                    stringResource(R.string.text_or).uppercase(),
                    fontSize = ThreeQuarteredDoubleDefaultSp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(DefaultDp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors()
                        .copy(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = stringResource(R.string.label_card_sign_in).uppercase(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = ThreeQuarteredDoubleDefaultSp
                    )
                }
                Spacer(Modifier.height(DefaultDp))
                Text(
                    stringResource(R.string.text_privacy_policy),
                    color = Color.White,
                    fontSize = HalfDefaultSp,
                    lineHeight = HalfDefaultSp
                )
                Spacer(Modifier.height(DefaultDp))
                Button(onClick = {}, Modifier.align(Alignment.Start)) {
                    Icon(Icons.Default.Language, contentDescription = "Choose Language Icon")
                    Spacer(Modifier.width(HalfDefaultDp))
                    Text(
                        text = stringResource(R.string.label_for_arabic_click_here),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            Image(
                painterResource(R.drawable.bg_image_login_screen),
                contentDescription = null,
                Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@PreviewTablet
@Composable
private fun LoginScreenPreview() {
    SmartDrivingTestExaminerNewTheme {
        LoginScreenUI()
    }
}
