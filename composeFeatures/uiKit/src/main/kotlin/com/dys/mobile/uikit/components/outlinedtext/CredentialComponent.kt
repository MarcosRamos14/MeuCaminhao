package com.dys.mobile.uikit.components.outlinedtext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.uikit.R
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.transformation.PhoneVisualTransformation
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray70
import com.dys.mobile.uikit.theme.Gray95
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun CredentialComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    placeHolder: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLines: Int = Int.MAX_VALUE,
    value: String,
    onValueChange: (String) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8._dph)
    ) {
        title?.let {
            TextComponent(
                text = it,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                TextComponent(
                    text = placeHolder ?: "",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisible) {
                        painterResource(R.drawable.ic_visible)
                    } else {
                        painterResource(R.drawable.ic_invisible)
                    }

                    IconButton(
                        modifier = Modifier.padding(end = 8._dpw),
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = null
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Gray70,
                focusedContainerColor = Gray95,
                unfocusedContainerColor = Gray95,
            ),
            maxLines = maxLines,
            shape = MaterialTheme.shapes.extraLarge,
            visualTransformation = if (isPassword && passwordVisible) {
                PasswordVisualTransformation()
            } else if (keyboardType == KeyboardType.Phone) {
                PhoneVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )
        )
    }
}

@Preview
@Composable
private fun CredentialComponentPreview() {
    MeuCaminhaoTheme {
        CredentialComponent(
            title = "Email",
            value = ""
        )
    }
}