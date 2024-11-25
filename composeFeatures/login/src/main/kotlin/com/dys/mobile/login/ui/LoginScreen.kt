package com.dys.mobile.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.CredentialComponent
import com.dys.mobile.uikit.components.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.TextButtonComponent
import com.dys.mobile.uikit.components.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextComponent(
                text = stringResource(R.string.text_control_your_trips),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            TextComponent(
                text = stringResource(R.string.text_start_here),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(60.dp))

            Credentials()

            LoginOptions()
        }
    }
}

@Composable
fun Credentials() {
    TextComponent(
        text = stringResource(R.string.text_access_your_app),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(16.dp))

    CredentialComponent(
        title = stringResource(R.string.text_email),
        placeHolder = stringResource(R.string.text_email_placeholder),
        onValueChange = { /*TODO*/ },
    )

    Spacer(modifier = Modifier.height(16.dp))

    CredentialComponent(
        title = stringResource(R.string.text_password),
        placeHolder = stringResource(R.string.text_password_placeholder),
        isPassword = true,
        onValueChange = { /*TODO*/ },
    )
}

@Composable
fun LoginOptions() {
    TextButtonComponent(
        text = stringResource(R.string.text_forgot_my_password),
        style = MaterialTheme.typography.bodyMedium,
        contentPadding = PaddingValues(0.dp),
        onClick = { /*TODO*/ }
    )

    Spacer(modifier = Modifier.height(56.dp))

    FilledRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        text = stringResource(R.string.text_access),
        onClick = { /*TODO*/ }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = stringResource(R.string.text_or),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        text = stringResource(R.string.text_access_with_google),
        icon = R.drawable.ic_google,
        contentPadding = PaddingValues(0.dp),
        onClick = { /*TODO*/ }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = stringResource(R.string.text_dont_have_account),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )

    TextButtonComponent(
        text = stringResource(R.string.text_register),
        style = MaterialTheme.typography.bodyMedium,
        horizontalArrangement = Arrangement.Center,
        onClick = { /*TODO*/ }
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    MeuCaminhaoTheme {
        LoginScreen()
    }
}