package com.dys.mobile.uikit.components.images

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = Shapes.medium,
    fallbackColor: Color = Color.Unspecified,
    @DrawableRes fallback: Int = R.drawable.ic_image,
    @StringRes contentDescription: Int,
    openImageClick: () -> Unit
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(shape = shape)
            .fillMaxWidth()
            .clickable { openImageClick() },
        model = url,
        contentDescription = stringResource(contentDescription),
        contentScale = contentScale,
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray80),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(0.25f),
                    painter = painterResource(fallback),
                    contentDescription = stringResource(R.string.text_error_loading_image),
                    tint = fallbackColor
                )
            }
        },
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}