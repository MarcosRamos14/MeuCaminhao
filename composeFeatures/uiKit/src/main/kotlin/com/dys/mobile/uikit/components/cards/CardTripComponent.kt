package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndPlateComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

// TODO: Alterar Data Class de acordo com retorno do back
data class TripDTO(
    val id: Int,
    val driver: String,
    val vehiclePlate: String,
    val startDate: String,
    val incomeTotal: String
)

@Composable
fun CardTripComponent(
    trips: TripDTO,
    onClick: () -> Unit = {}
) {
    Card(
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16._dph)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMessageTextComponent(
                        title = R.string.text_driver,
                        message = trips.driver
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndPlateComponent(
                        plateValue = trips.vehiclePlate
                    )
                }

                Spacer(modifier = Modifier.height(14._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndTimeComponent(
                        title = R.string.text_date,
                        time = trips.startDate
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMonetaryComponent(
                        title = R.string.text_profit_loss,
                        value = trips.incomeTotal,
                        type = MonetaryValueType.POSITIVE
                    )
                }
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
                    driver = "Fulano de Tal",
                    vehiclePlate = "IUX7H564",
                    startDate = "Ago 23, 2025",
                    incomeTotal = "R$ 100,00"
                )
            )
        }
    }
}