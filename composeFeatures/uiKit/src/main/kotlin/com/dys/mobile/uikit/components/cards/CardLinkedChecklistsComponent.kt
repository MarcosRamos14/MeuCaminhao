package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleChecklistDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Gray90
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardLinkedChecklistsComponent(
    modifier: Modifier = Modifier,
    checklist: VehicleChecklistDTO,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(bottom = 16._dph)
            .border(1.dp, Gray80, shape = Shapes.large),
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
                    modifier = Modifier.fillMaxWidth(),
                    title = R.string.common_checklist,
                    message = checklist.name,
                    messageMaxLin = 1
                )

                Spacer(modifier = Modifier.height(16._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_last_review,
                        time = checklist.lastReview ?: stringResource(R.string.common_date_not_defined)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_periodicity,
                        message = checklist.periodicity ?: stringResource(R.string.common_error_getting_name),
                        messageMaxLin = 1
                    )
                }
            }

            HorizontalDivider(color = Gray80, thickness = 1.dp)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Gray90)
            ) {
                TextButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.text_checklist_management),
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
private fun CardLinkedChecklistsPreview() {
    MeuCaminhaoTheme {
        CardLinkedChecklistsComponent(
            checklist = VehicleChecklistDTO(
                id = 0,
                name = "Revisão pré-jornada",
                lastReview = "Ago 23, 2025",
                periodicity = "A cada 15 dias"
            ),
            onClick = { }
        )
    }
}