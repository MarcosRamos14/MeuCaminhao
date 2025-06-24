package com.dys.mobile.uikit.screens.photos

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent

@Composable
fun PhotoGalleryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    photos: List<String>
) {
    val photosAsString = photos.joinToString(",") { Uri.encode(it) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_all_photos)
            )
        },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            itemsIndexed(photos) { index, photoUrl ->
                Box(
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate(Routes.FullPhotoScreen.routeWithArgs(
                                    url = photosAsString,
                                    index = index
                                ))
                            },
                        model = photoUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}