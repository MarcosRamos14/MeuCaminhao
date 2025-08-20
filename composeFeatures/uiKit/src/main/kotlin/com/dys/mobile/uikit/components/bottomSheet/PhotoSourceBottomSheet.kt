package com.dys.mobile.uikit.components.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSourceBottomSheet(
    onTakePhotoClicked: () -> Unit,
    onChooseFromGalleryClicked: () -> Unit,
    onDismissRequest: (() -> Unit)? = null
) {
    val sheetState = rememberModalBottomSheetState()
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
                .padding(horizontal = 16._dpw),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20._dph)
        ) {
            Row(
                modifier = Modifier.clickable {
                    hideSheetAndRunAction { onTakePhotoClicked() }
                    onDismissRequest?.invoke()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32._dpw)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = null,
                    tint = Black
                )

                TextComponent(
                    text = stringResource(R.string.text_take_photo),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                modifier = Modifier.clickable {
                    hideSheetAndRunAction { onChooseFromGalleryClicked() }
                    onDismissRequest?.invoke()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32._dpw)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_image_gallery),
                    contentDescription = null,
                    tint = Black
                )

                TextComponent(
                    text = stringResource(R.string.text_choose_from_gallery),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun PhotoSourceBottomSheetPreview() {
    /**
     * use 'rememberStandardBottomSheetState' to view the bottomSheet in the preview, as
     * 'rememberModalBottomSheetState' by default sets the bottomSheet state to Hidden.
     *
     * ⚠️ Warning:
     * Using 'rememberStandardBottomSheetState' outside of a preview context (e.g., in test) can
     * throw an IllegalStateException when calling 'dismiss()' or 'hide()' if 'skipHiddenState' is
     * true (which is the default).
     */

    MeuCaminhaoTheme {
        PhotoSourceBottomSheet(
            onTakePhotoClicked = { },
            onChooseFromGalleryClicked = { }
        )
    }
}