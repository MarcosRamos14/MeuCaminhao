package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.TripGeneralInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.TripIncomeDTO
import com.dys.mobile.toolkit.extensions.FileType
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.openGoogleMaps
import com.dys.mobile.toolkit.extensions.toFileType
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndPlateComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardTripGeneralInfoComponent(
    modifier: Modifier = Modifier,
    generalInfo: TripGeneralInfoDTO?,
    income: TripIncomeDTO?
) {
    val context = LocalContext.current

    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16._dph),
            ) {
                TextAndMessageTextComponent(
                    title = R.string.text_driver,
                    message = generalInfo?.driver ?: stringResource(R.string.common_error_getting_name)
                )

                Spacer(modifier = Modifier.height(16._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_start_time,
                        time = generalInfo?.startTime ?: stringResource(R.string.common_time_not_defined)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        timeFontWeight = FontWeight.SemiBold,
                        icon = R.drawable.ic_clock,
                        title = R.string.text_duration,
                        time = generalInfo?.duration ?: stringResource(R.string.common_time_not_defined)
                    )
                }

                Spacer(modifier = Modifier.height(16._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_net_total_value,
                        type = MonetaryValueType.NEUTRAL,
                        value = income?.totalAmount?.formatted ?: stringResource(R.string.common_default_price)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_loaded_weight,
                        message = generalInfo?.weight?.formatted ?:  stringResource(R.string.common_error_getting_weight)
                    )
                }

                Spacer(modifier = Modifier.height(16._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_balance,
                        type = MonetaryValueType.NEUTRAL,
                        value = income?.balanceReceivable?.formatted ?: stringResource(R.string.common_default_price)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndPlateComponent(
                        modifier = Modifier.weight(1f),
                        plateValue = generalInfo?.vehiclePlate ?:  stringResource(R.string.common_vehicle_plate_not_defined)
                    )
                }

                Spacer(modifier = Modifier.height(16._dph))

                CardCourseInfoComponent(
                    origin = generalInfo?.course?.origin ?: stringResource(R.string.common_error_getting_origin),
                    destination = generalInfo?.course?.destination ?: stringResource(R.string.common_error_getting_destination),
                    showMapOnClick = {
                        val originLat = generalInfo?.course?.originLatitude
                        val originLng = generalInfo?.course?.originLongitude
                        val destinationLat = generalInfo?.course?.destinationLatitude
                        val destinationLng = generalInfo?.course?.destinationLongitude

                        if (originLat != null && originLng != null && destinationLat != null && destinationLng != null) {
                            context.openGoogleMaps(
                                originLatitude = originLat,
                                originLongitude = originLng,
                                destinationLatitude = destinationLat,
                                destinationLongitude = destinationLng
                            )
                        } else {
                            // TODO: Show snackBar (outra branch implementação)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16._dph))

                TextComponent(
                    modifier = Modifier.wrapContentWidth(),
                    text = stringResource(R.string.text_manifest),
                    color = Gray65,
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4._dph))

                val manifestUrl = generalInfo?.manifestUrl

                when (manifestUrl?.toFileType()) {
                    FileType.PDF -> {
                        CardDownloadFileComponent(
                            fileName = "Manifest.pdf", //TODO
                            onClick = {
                                //TODO
                            }
                        )
                    }
                    FileType.IMAGE -> {
                        ImageComponent(
                            url = manifestUrl,
                            contentDescription = R.string.text_manifest_image
                        )
                    }
                    else -> { }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardTripGeneralInfoPreview() {
    MeuCaminhaoTheme {
        CardTripGeneralInfoComponent(
            generalInfo = null,
            income = null
        )
    }
}