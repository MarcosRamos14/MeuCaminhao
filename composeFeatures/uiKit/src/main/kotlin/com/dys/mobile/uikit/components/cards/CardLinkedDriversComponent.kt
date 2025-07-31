package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.meucaminhao.domain.dto.ComponentDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDriverDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Green60
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardLinkedDriversComponent(
    modifier: Modifier = Modifier,
    driver: VehicleDriverDTO,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 16._dph)
            .border(1.dp, Gray80, shape = Shapes.large)
            .clickable { onClick() },
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue80)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16._dpw, vertical = 8._dph),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageComponent(
                        modifier = Modifier
                            .width(36._dpw)
                            .height(36._dph)
                            .clip(CircleShape),
                        url = driver.photoUrl ?: "",
                        contentDescription = R.string.text_user_photo,
                        fallback = R.drawable.ic_person_filled,
                        openImageClick = { }
                    )

                    Spacer(modifier = Modifier.width(8._dpw))

                    TextComponent(
                        modifier = Modifier.weight(1f),
                        text = driver.name,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                }
            }

            Row(
                modifier = Modifier.padding(horizontal = 16._dpw, vertical = 16._dph),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextAndTimeComponent(
                    modifier = Modifier.weight(1f),
                    title = R.string.text_last_review,
                    time = driver.leftInfo?.value ?: stringResource(R.string.common_date_not_defined)
                )

                Spacer(modifier = Modifier.width(16._dpw))

                TextAndMonetaryComponent(
                    modifier = Modifier.weight(1f),
                    type = MonetaryValueType.POSITIVE,
                    title = R.string.text_number_of_trips,
                    value = driver.rightInfo?.value ?: stringResource(R.string.common_default_price),
                    valueColor = Green60
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardLinkedDriversPreview() {
    MeuCaminhaoTheme {
        CardLinkedDriversComponent(
            driver = VehicleDriverDTO(
                id = 123,
                name = "Marcos Ramos",
                photoUrl = "",
                leftInfo = ComponentDTO(
                    overline = "Data de cadastro",
                    value = "Ago 23, 2025",
                    accentColor = "",
                    backgroundColor = ""
                ),
                rightInfo = ComponentDTO(
                    overline = "Qtd. viagens",
                    value = "854",
                    accentColor = "",
                    backgroundColor = ""
                )
            ),
            onClick = { }
        )
    }
}