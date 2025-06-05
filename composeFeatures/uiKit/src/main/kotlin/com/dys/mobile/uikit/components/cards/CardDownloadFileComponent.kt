package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray90
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardDownloadFileComponent(
    modifier: Modifier = Modifier,
    fileName: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Gray90),
        content = {
            Row(
                modifier = Modifier.padding(vertical = 16._dph, horizontal = 16._dpw),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_file),
                    contentDescription = stringResource(R.string.text_download_file),
                    tint = Color.Unspecified
                )

                TextComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8._dpw),
                    text = fileName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

                Icon(
                    painter = painterResource(R.drawable.ic_download),
                    contentDescription = stringResource(R.string.text_download_file),
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardDownloadFilePreview() {
    MeuCaminhaoTheme {
        CardDownloadFileComponent(
            fileName = "Manifesto.pdf",
            onClick = {}
        )
    }
}