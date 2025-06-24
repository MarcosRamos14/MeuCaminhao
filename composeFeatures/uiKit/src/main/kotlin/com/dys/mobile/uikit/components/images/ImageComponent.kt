package com.dys.mobile.uikit.components.images

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes fallback: Int = R.drawable.ic_broken_image,
    @StringRes contentDescription: Int,
    url: String,
    openImageClick: () -> Unit
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(shape = Shapes.medium)
            .fillMaxWidth()
            .clickable { openImageClick() },
        model = url,
        contentDescription = stringResource(contentDescription),
        contentScale = contentScale,
        error = {
            Icon(
                painter = painterResource(fallback),
                contentDescription = stringResource(R.string.text_error_loading_image)
            )
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