package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
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
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndPlateComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Gray95
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardVehicleComponent(
    vehicle: VehicleDTO,
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
                    TextAndPlateComponent(
                        modifier = Modifier.weight(1f),
                        plateValue = vehicle.plate
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_registration_date,
                        time = vehicle.createdAt ?: stringResource(R.string.common_date_not_defined)
                    )
                }

                Spacer(modifier = Modifier.height(14._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_total_income,
                        type = MonetaryValueType.POSITIVE,
                        value = vehicle.totalIncome?.formatted ?: stringResource(R.string.common_default_price)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_total_spent,
                        type = MonetaryValueType.NEGATIVE,
                        value = vehicle.totalExpense?.formatted ?: stringResource(R.string.common_default_price)
                    )
                }
            }

            HorizontalDivider(color = Gray80, thickness = 1.dp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray95)
            ) {
                TextButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.common_text_see_more),
                    style = MaterialTheme.typography.bodyMedium,
                    icon = R.drawable.ic_see_more,
                    iconPosition = false,
                    horizontalArrangement = Arrangement.Center,
                    onClick = onClick
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardVehicleComponentPreview() {
    MeuCaminhaoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray80),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardVehicleComponent(
                vehicle = VehicleDTO(
                    id = 1,
                    plate = "AAA-1234",
                    createdAt = "Ago 23, 2025",
                    totalIncome = TotalAmountDTO(
                        value = null,
                        currency = null,
                        formatted = "R$ 7.423,47",
                        isPositive = true
                    ),
                    totalExpense = TotalAmountDTO(
                        value = null,
                        currency = null,
                        formatted = "R$ 2.157,18",
                        isPositive = false
                    )
                )
            )
        }
    }
}