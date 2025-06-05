package com.dys.mobile.uikit.components.images

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.uikit.R

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    url: String,
    @StringRes contentDescription: Int
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(120._dph),
        model = url,
        contentDescription = stringResource(contentDescription),
        contentScale = contentScale,
        error = {
            Icon(
                painter = painterResource(R.drawable.ic_broken_image),
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