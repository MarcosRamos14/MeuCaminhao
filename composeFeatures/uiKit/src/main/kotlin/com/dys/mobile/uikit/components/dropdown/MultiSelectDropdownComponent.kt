package com.dys.mobile.uikit.components.dropdown

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.Gray70
import com.dys.mobile.uikit.theme.Gray95
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

data class DropdownItem(
    val title: String,
    val id: Long? = null,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiSelectDropdownComponent(
    modifier: Modifier = Modifier,
    @StringRes title: Int? = null,
    items: List<DropdownItem>,
    onSelectionChange: (List<DropdownItem>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var internalItems by remember { mutableStateOf(items) }

    val selectedItems = internalItems.filter { it.isSelected }
    val valueSelected = when (selectedItems.size) {
        0 -> ""
        1 -> selectedItems.first().title
        else -> stringResource(R.string.text_selected_multiple, selectedItems.size)
    }

    Column(
        modifier = modifier
    ) {
        title?.let {
            TextComponent(
                text = stringResource(it),
                color = Black,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8._dph))
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = valueSelected,
            shape = Shapes.extraLarge,
            readOnly = true,
            onValueChange = { },
            placeholder = {
                TextComponent(
                    text = stringResource(R.string.text_select),
                    color = Color.LightGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = {
                val icon = if (expanded) R.drawable.ic_up_cima else R.drawable.ic_down_arrow

                Icon(
                    modifier = Modifier.clickable { expanded = !expanded },
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.text_show_options),
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Gray70,
                focusedContainerColor = Gray95,
                unfocusedContainerColor = Gray95,
            )
        )

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        DropdownMenu(
            modifier = Modifier.width(screenWidth - (16._dpw * 2)),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = Shapes.large,
            containerColor = MaterialTheme.colorScheme.onBackground,
        ) {
            internalItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        TextComponent(
                            text = item.title,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1
                        )
                    },
                    trailingIcon = {
                        if (item.isSelected) {
                            Icon(
                                painter = painterResource(R.drawable.ic_check),
                                contentDescription = stringResource(R.string.text_selected),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    onClick = {
                        internalItems = internalItems.map {
                            if (it.title == item.title) it.copy(isSelected = !it.isSelected)
                            else it
                        }
                        onSelectionChange(
                            internalItems.filter { it.isSelected }
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun MultiSelectDropdownPreview() {
    MeuCaminhaoTheme {
        MultiSelectDropdownComponent(
            items = listOf(
                DropdownItem(title = "Item 1"),
                DropdownItem(title = "Item 2"),
                DropdownItem(title = "Item 3"),
                DropdownItem(title = "Item 4"),
                DropdownItem(title = "Item 5"),
            ),
            onSelectionChange = { }
        )
    }
}