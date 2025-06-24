package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.stepper.StepperRouteComponent
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardCourseInfoComponent(
    modifier: Modifier = Modifier,
    origin: String,
    destination: String,
    showMapOnClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Blue80
        ),
        content = {
            Column(
                modifier = Modifier.padding(bottom = 16._dph, start = 16._dpw, end = 16._dpw),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextComponent(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.text_course),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    TextButtonComponent(
                        modifier = Modifier,
                        text = stringResource(R.string.text_view_on_map),
                        style = MaterialTheme.typography.bodySmall,
                        icon = R.drawable.ic_see_more,
                        iconPosition = false,
                        contentPadding = PaddingValues(0._dph),
                        onClick = showMapOnClick
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    StepperRouteComponent()

                    Spacer(modifier = Modifier.width(8._dpw))

                    Column {
                        TextAndMessageTextComponent(
                            title = R.string.text_start_date_prefix,
                            message = origin,
                            messageMaxLin = 1
                        )

                        Spacer(modifier = Modifier.height(8._dph))

                        TextAndMessageTextComponent(
                            title = R.string.text_to_prefix,
                            message = destination,
                            messageMaxLin = 1
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardCourseInfoPreview() {
    MeuCaminhaoTheme {
        CardCourseInfoComponent(
            origin = "Av. Baltazar de O. Garcia, 1456 Minas Gerais",
            destination = "Av. Fernando de O. Garcia, 3467 Minas Gerais",
            showMapOnClick = {}
        )
    }
}