package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleInfoDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndPlateComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardVehicleGeneralInfoComponent(
    modifier: Modifier = Modifier,
    vehicleInfo: VehicleInfoDTO?,
    openPhotoClick: (String?) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column {
                ImageComponent(
                    modifier = Modifier.height(120._dph),
                    url = vehicleInfo?.photoUrl ?: "",
                    contentDescription = R.string.text_vehicle_image,
                    openImageClick = { openPhotoClick(vehicleInfo?.photoUrl ?: "") }
                )

                Row(
                    modifier = Modifier.padding(vertical = 16._dph, horizontal = 16._dpw),
                    horizontalArrangement = Arrangement.spacedBy(8._dpw)
                ) {
                    TextAndPlateComponent(
                        modifier = Modifier.weight(1f),
                        plateValue = vehicleInfo?.plate ?: stringResource(R.string.common_error_getting_data)
                    )

                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_registration_date,
                        time = vehicleInfo?.createdAt ?: stringResource(R.string.common_error_getting_data),
                    )
                }

                Row(
                    modifier = Modifier.padding(
                        start = 16._dpw,
                        end = 16._dpw,
                        bottom = 16._dph
                    ),
                    horizontalArrangement = Arrangement.spacedBy(8._dpw)
                ) {
                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_make,
                        message = vehicleInfo?.brand ?: stringResource(R.string.common_error_getting_data)
                    )

                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_model,
                        message = vehicleInfo?.model ?: stringResource(R.string.common_error_getting_data)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardVehicleGeneralInfoPreview() {
    MeuCaminhaoTheme {
        CardVehicleGeneralInfoComponent(
            vehicleInfo = VehicleInfoDTO(
                id = 123,
                plate = "AAA-1234",
                brand = "Scania",
                model = "R 540",
                createdAt = "Ago 23, 2024",
                photoUrl = "",
                history = emptyList(),
                drivers = emptyList(),
                checklists = emptyList()
            )
        )
    }
}