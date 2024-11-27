package com.dys.mobile.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.theme.Gray90
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun CredentialComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    placeHolder: String? = null,
    isPassword: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        modifier = Modifier.padding(end = 8.dp),
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
                unfocusedIndicatorColor = Color.LightGray,
                focusedContainerColor = Gray90,
                unfocusedContainerColor = Gray90,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            visualTransformation = if (isPassword && passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )
    }
}

@Preview
@Composable
private fun CredentialComponentPreview() {
    MeuCaminhaoTheme {

    }
}