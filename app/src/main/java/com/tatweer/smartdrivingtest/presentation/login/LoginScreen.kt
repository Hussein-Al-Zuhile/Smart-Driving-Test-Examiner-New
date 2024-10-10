package com.tatweer.smartdrivingtest.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.tatweer.smartdrivingtest.R
import com.tatweer.smartdrivingtest.presentation.base.PreviewTablet
import com.tatweer.smartdrivingtest.presentation.theme.DefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.DoubleDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultDp
import com.tatweer.smartdrivingtest.presentation.theme.HalfDefaultSp
import com.tatweer.smartdrivingtest.presentation.theme.AppTheme
import com.tatweer.smartdrivingtest.presentation.theme.ThreeQuarteredDoubleDefaultSp


@Composable
fun LoginScreen(
    state: LoginScreenState,
    onEvent: (LoginScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier) {
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
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    stringResource(R.string.text_please_enter_your_military_id_password),
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.height(HalfDefaultDp))
                OutlinedTextField(
                    state.militaryID,
                    onValueChange = { onEvent(LoginScreenEvent.OnMilitaryIDChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
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

                OutlinedTextField(
                    state.password,
                    onValueChange = { onEvent(LoginScreenEvent.OnPasswordChanged(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(
                            stringResource(R.string.label_password),
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = ThreeQuarteredDoubleDefaultSp
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(LoginScreenEvent.OnPasswordVisibilityToggled)
                            }
                        ) {
                            if (state.isPasswordVisible) {
                                Icon(Icons.Default.VisibilityOff, "Hide Password")
                            } else {
                                Icon(Icons.Default.Visibility, "See Password Button")
                            }
                        }
                    },
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                )
                Spacer(Modifier.height(DoubleDefaultDp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(
                        text = stringResource(R.string.label_sign_in).uppercase(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = ThreeQuarteredDoubleDefaultSp,
                        modifier = Modifier.padding(vertical = HalfDefaultDp)
                    )
                }
                Spacer(Modifier.height(DefaultDp))
                Text(
                    stringResource(R.string.text_or).uppercase(),
                    fontSize = ThreeQuarteredDoubleDefaultSp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(DefaultDp))
                Icon(
                    painterResource(R.drawable.ic_nfc),
                    contentDescription = "NFC Detection",
                    Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                )
                Text(
                    text = stringResource(R.string.label_card_sign_in).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = ThreeQuarteredDoubleDefaultSp,
                    modifier = Modifier
                        .padding(vertical = HalfDefaultDp)
                )

                Spacer(Modifier.height(DefaultDp))
                Text(
                    stringResource(R.string.text_privacy_policy),
                    fontSize = HalfDefaultSp,
                    lineHeight = HalfDefaultSp
                )
                Spacer(Modifier.height(DefaultDp))
                TextButton(onClick = {}, Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(R.drawable.ic_language),
                        contentDescription = "Choose Language Icon",
                        Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(HalfDefaultDp))
                    Text(
                        text = stringResource(R.string.label_for_arabic_click_here),
                        textDecoration = TextDecoration.Underline,
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
    AppTheme {
        var state by remember { mutableStateOf(LoginScreenState()) }
        LoginScreen(state, {
            when (it) {
                is LoginScreenEvent.OnMilitaryIDChanged -> {}
                is LoginScreenEvent.OnPasswordChanged -> {
                    state = state.copy(password = it.password)
                }

                else -> {}
            }
        })
    }
}
