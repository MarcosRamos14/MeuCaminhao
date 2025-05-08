package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripVehicleDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndPlateComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Gray95
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardTripComponent(
    trip: TripDTO,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(vertical = 8._dph, horizontal = 24._dpw),
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2._dpw
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16._dph)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_driver,
                        message = trip.driver
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndPlateComponent(
                        modifier = Modifier.weight(1f),
                        plateValue = trip.vehicle.plate
                    )
                }

                Spacer(modifier = Modifier.height(14._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_date,
                        time = trip.startAt
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_profit_loss,
                        type = MonetaryValueType.POSITIVE, // TODO: Alterar conforme retorno do back
                        value = trip.totalAmount // TODO: Alterar para valor positivo ou negativo de acordo com back
                    )
                }
            }

            HorizontalDivider(color = Gray80, thickness = 1.dp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray95)
                    .clickable(onClick = onClick)
            ) {
                TextButtonComponent(
                    text = stringResource(R.string.common_text_see_more),
                    style = MaterialTheme.typography.bodyMedium,
                    icon = R.drawable.ic_see_more,
                    iconPosition = false,
                    horizontalArrangement = Arrangement.Center
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardTripComponentPreview() {
    MeuCaminhaoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray80),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardTripComponent(
                TripDTO(
                    id = 1,
                    title = "Viagem para Rio de Janeiro",
                    origin = "Belo Horizonte",
                    destination = "Rio de Janeiro",
                    startAt = "Ago 23, 2025",
                    endAt = null,
                    wight = 15000.0,
                    manifestUrl = null,
                    driver = "Marcos Moreira Ramos",
                    totalAmount = "R$ 2250",
                    remainingAmount = "",
                    duration = "12:50:00",
                    status = "DONE",
                    weightAmount = "R$125,50",
                    vehicle = TripVehicleDTO(
                        id = 1,
                        plate = "QUB6J82",
                        model = "Volvo 540",
                        photoUrl = null,
                        owner = "Marcos Moreira Ramos"
                    )
                )
            )
        }
    }
}