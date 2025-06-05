package com.dys.mobile.uikit.components.bottomSheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDetailsBottomSheet(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes textPositiveButton: Int,
    @StringRes textNegativeButton: Int? = null,
    isMultipleOptions: Boolean = false,
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null
) {
    val sheetState = rememberStandardBottomSheetState()
    val scope = rememberCoroutineScope()

    fun hideSheetAndRunAction(action: () -> Unit) {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            action()
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            hideSheetAndRunAction { onDismissRequest?.invoke() }
        },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16._dph),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent(
                text = stringResource(title),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(message),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            if (isMultipleOptions) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8._dpw,
                        Alignment.CenterHorizontally
                    )
                ) {
                    OutlinedRoundButtonComponent(
                        modifier = Modifier.wrapContentWidth(),
                        modifierRow = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16._dpw),
                        text = stringResource(textNegativeButton ?: R.string.common_back),
                        fontWeight = FontWeight.Bold,
                        color = Red60,
                        onClick = {
                            hideSheetAndRunAction { onNegativeButtonClicked?.invoke() }
                        }
                    )

                    FilledRoundButtonComponent(
                        modifier = Modifier.wrapContentWidth(),
                        modifierText = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16._dpw),
                        text = stringResource(textPositiveButton),
                        onClick = {
                            hideSheetAndRunAction(onPositiveButtonClicked)
                        }
                    )
                }
            } else {
                FilledRoundButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60._dpw),
                    text = stringResource(textPositiveButton),
                    onClick = {
                        hideSheetAndRunAction(onPositiveButtonClicked)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32._dph))
        }
    }
}

@Preview
@Composable
private fun ExpenseDetailsBottomSheetPreview() {
    /**
     * use 'rememberStandardBottomSheetState' to view the bottomSheet in the preview, as
     * 'rememberModalBottomSheetState' by default sets the bottomSheet state to Hidden.
     */

    MeuCaminhaoTheme {
        CommonBottomSheet(
            icon = R.drawable.thumbnail_check_circle,
            title = R.string.text_password_changed_successfully,
            message = R.string.text_password_reset_success_message,
            textPositiveButton = R.string.text_action_login,
            textNegativeButton = R.string.common_back,
            isMultipleOptions = true,
            onPositiveButtonClicked = {}
        )
    }
}