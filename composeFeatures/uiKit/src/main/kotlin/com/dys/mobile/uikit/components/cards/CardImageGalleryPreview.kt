package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardImageGalleryPreview(
    modifier: Modifier = Modifier,
    imageUrls: List<String>,
    onClick: () -> Unit,
) {
    val previewImages = imageUrls.take(4)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = Shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            for (row in 0 until 2) {
                Row(
                    Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    for (col in 0 until 2) {
                        val index = row * 2 + col
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            if (index < previewImages.size) {
                                ImageComponent(
                                    contentScale = ContentScale.Crop,
                                    url = previewImages[index],
                                    contentDescription = R.string.text_all_photos,
                                    modifier = Modifier.fillMaxSize(),
                                    openImageClick = onClick
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .clip(shape = Shapes.medium)
                                        .fillMaxSize()
                                        .background(Color.LightGray)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(1._dpw))
                    }
                }

                Spacer(modifier = Modifier.height(1._dph))
            }
        }
    }
}

@Preview
@Composable
private fun CardImageGalleryPrev() {
    MeuCaminhaoTheme {
        CardImageGalleryPreview(
            imageUrls = emptyList(),
            onClick = { }
        )
    }
}