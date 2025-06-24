package com.dys.mobile.uikit.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.cards.CardExpenseGeneralInfoComponent
import com.dys.mobile.uikit.components.cards.CardImageGalleryPreview
import com.dys.mobile.uikit.components.cards.CardInformativeComponent
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDetailsBottomSheet(
    expenseItem: ExpenseItemDTO,
    openPhotoTicketClick: (String) -> Unit,
    openAdditionalPhotosClick: (String) -> Unit,
    deleteExpenseClick: () -> Unit,
    editExpenseClick: () -> Unit,
    onDismissRequest: (() -> Unit)? = null
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
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
                .padding(horizontal = 16._dph)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.text_expense, expenseItem.category?.name ?: ""),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(32._dph))

            CardExpenseGeneralInfoComponent(
                categoryType = expenseItem.category?.type,
                expenseItemInfo = expenseItem.generalInformation,
            )

            Spacer(modifier = Modifier.height(24._dph))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16._dpw)
            ) {
                val ticketUrl = expenseItem.ticketUrl.orEmpty()
                val fallbackIcon = if (ticketUrl.isNotEmpty()) R.drawable.ic_broken_image else R.drawable.ic_camera

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    TextComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4._dph),
                        text = stringResource(R.string.text_invoice),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        color = Gray65,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    ImageComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        url = expenseItem.ticketUrl ?: "",
                        contentDescription = R.string.text_invoice,
                        fallback = fallbackIcon,
                        openImageClick = {
                            openPhotoTicketClick(expenseItem.ticketUrl ?: "")
                        }
                    )

                    Spacer(modifier = Modifier.height(24._dph))
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    TextComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4._dph),
                        text = stringResource(R.string.text_additional_photos),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        color = Gray65,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    val quantityAdditionalResource = expenseItem.additionalResources?.quantity ?: 0
                    val urlList = expenseItem
                        .additionalResources
                        ?.resources
                        ?.mapNotNull { it.url?.takeIf { url -> url.isNotBlank() } }
                        ?: emptyList()
                    val urlsAsString = urlList.joinToString(separator = ",")

                    if (quantityAdditionalResource > 0) {
                        CardImageGalleryPreview(
                            imageUrls = urlList,
                            onClick = {
                                openAdditionalPhotosClick(urlsAsString)
                            }
                        )
                    } else {
                        ImageComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            url = expenseItem.ticketUrl ?: "",
                            contentDescription = R.string.text_invoice,
                            fallback = R.drawable.ic_camera,
                            openImageClick = { }
                        )
                    }

                    Spacer(modifier = Modifier.height(24._dph))
                }
            }

            if (!expenseItem.observation.isNullOrEmpty()) {
                TextComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.common_observation),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Light,
                    color = Gray65,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4._dph))

                CardInformativeComponent(
                    title = expenseItem.observation
                )

                Spacer(modifier = Modifier.height(32._dph))
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8._dpw)
            ) {
                OutlinedRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.text_delete_expense),
                    fontWeight = FontWeight.Bold,
                    color = Red60,
                    onClick = deleteExpenseClick
                )

                FilledRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.text_edit_expense),
                    onClick = editExpenseClick
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
     *
     * ⚠️ Warning:
     * Using 'rememberStandardBottomSheetState' outside of a preview context (e.g., in test) can
     * throw an IllegalStateException when calling 'dismiss()' or 'hide()' if 'skipHiddenState' is
     * true (which is the default).
     */

    MeuCaminhaoTheme {
        ExpenseDetailsBottomSheet(
            expenseItem = ExpenseItemDTO(
                expenseId = 1,
                title = "Despesa ocasional",
                vehiclePlate = "AAB-1234",
                category = null,
                amount = null,
                generalInformation = null,
                ticketUrl = "",
                additionalResources = null,
                observation = "Troca do opneu que estourou durante a trazetória."
            ),
            openPhotoTicketClick = { },
            openAdditionalPhotosClick = { },
            deleteExpenseClick = { },
            editExpenseClick = { }
        )
    }
}