package com.dys.mobile.uikit.components.chips

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.Blue65
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.White

@SuppressLint("SupportAnnotationUsage")
@Composable
fun <T : Enum<T>> FilterChipGroupComponent(
    modifier: Modifier = Modifier,
    options: Array<T>,
    selectedOption: T,
    @StringRes getLabelRes: (T) -> Int,
    onOptionSelected: (T) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        HorizontalDivider(color = Gray80, thickness = 1.dp)

        LazyRow(
            modifier = Modifier.padding(vertical = 8._dph),
            horizontalArrangement = Arrangement.spacedBy(8._dpw),
            contentPadding = PaddingValues(horizontal = 16._dpw)
        ) {
            items(options.size) { index ->
                val option = options[index]
                FilterChipComponent(
                    text = getLabelRes(option),
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) }
                )
            }
        }

        HorizontalDivider(color = Gray80, thickness = 1.dp)
    }
}

@Composable
private fun FilterChipComponent(
    @StringRes text: Int,
    selected: Boolean,
    onClick: () -> Unit = {}
) {
    val containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent
    val borderColor = if (!selected) Blue65 else Color.Transparent
    val textColor = if (selected) White else Black

    FilterChip(
        onClick = onClick,
        label = {
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(text),
                color = if (selected) White else Black,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        },
        shape = MaterialTheme.shapes.large,
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = containerColor,
            containerColor = Color.Transparent,
            selectedLabelColor = textColor,
            labelColor = textColor
        ),
        border = BorderStroke(
            width = 1._dph,
            color = borderColor
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun AssistChipSelectedPreview() {
    MeuCaminhaoTheme {
        FilterChipComponent(
            text = R.string.text_trips,
            selected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AssistChipUnselectedPreview() {
    MeuCaminhaoTheme {
        FilterChipComponent(
            text = R.string.text_trips,
            selected = false,
            onClick = {}
        )
    }
}