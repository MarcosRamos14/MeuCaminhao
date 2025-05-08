package com.dys.mobile.uikit.components.search

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray40
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Gray85
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit,
    @StringRes placeHolder: Int,
    uppercase: Boolean = false,
    maxLength: Int? = null,
    hasDividerTop: Boolean = true,
    hasDividerBottom: Boolean = true,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        if (hasDividerTop) {
            HorizontalDivider(color = Gray80, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8._dph))
        }

        SearchBar(
            modifier = Modifier
                .padding(start = 16._dpw, end = 16._dpw, bottom = 16._dph)
                .defaultMinSize(minHeight = 42._dph),
            query = query,
            onQueryChange = { newText ->
                val transformed = if (uppercase) newText.uppercase() else newText
                val limitedText = maxLength?.let { transformed.take(it) } ?: transformed
                onQueryChange(limitedText)
            },
            onSearch = { },
            active = false,
            onActiveChange = {},
            placeholder = {
                TextComponent(
                    text = stringResource(placeHolder),
                    color = Gray40,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.text_search_icon_description),
                    tint = Color.Unspecified
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = Gray85
            ),
            content = {},
        )

        if (hasDividerBottom) HorizontalDivider(color = Gray80, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarComponentPreview() {
    MeuCaminhaoTheme {
        SearchBarComponent(
            query = "",
            onQueryChange = { },
            placeHolder = R.string.text_search_by_plate,
            uppercase = true,
            maxLength = 7,
            hasDividerTop = false,
            hasDividerBottom = false,
        )
    }
}