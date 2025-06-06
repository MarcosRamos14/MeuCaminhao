package com.dys.mobile.trips.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.viewmodels.trips.TripsViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.cards.CardTripComponent
import com.dys.mobile.uikit.components.chips.FilterChipGroupComponent
import com.dys.mobile.uikit.components.date.DatePickerComponent
import com.dys.mobile.uikit.components.enum.TripStatusFilterEnum
import com.dys.mobile.uikit.components.search.SearchBarComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val LICENSE_PLATE_SIZE = 7

@Composable
fun TripsScreen(navController: NavController) {
    val viewModel = koinViewModel<TripsViewModel>()

    TripsContent(viewModel)
    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun TripsContent(viewModel: TripsViewModel) {
    val mockTrips: List<TripDTO> = viewModel.getMockList()

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val showFilters by remember {
        derivedStateOf {
            ((listState.firstVisibleItemIndex == 0) && (listState.firstVisibleItemScrollOffset < 50))
        }
    }
    val showScrollToTopButton by remember {
        derivedStateOf {
            ((listState.firstVisibleItemIndex > 0) || (listState.firstVisibleItemScrollOffset > 300))
        }
    }
    var queryState by rememberSaveable { mutableStateOf("") }
    var selectedTripStatus by rememberSaveable { mutableStateOf(TripStatusFilterEnum.ALL) }
    var startDateState by rememberSaveable { mutableStateOf("") }
    var endDateState by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_trips),
                hasDivider = !showFilters
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showScrollToTopButton,
                enter = fadeIn(tween(400)),
                exit = fadeOut(tween(400))
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_keyboard_arrow_up),
                        contentDescription = stringResource(R.string.text_go_to_top),
                        tint = Color.White
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = showFilters,
                enter = fadeIn(animationSpec = tween(durationMillis = 400)) +
                        expandVertically(animationSpec = tween(durationMillis = 400)),
                exit = fadeOut(animationSpec = tween(durationMillis = 400)) +
                        shrinkVertically(animationSpec = tween(durationMillis = 400))
            ) {
                Column {
                    SearchBarComponent(
                        query = queryState,
                        onQueryChange = { queryState = it },
                        placeHolder = R.string.text_search_by_plate,
                        uppercase = true,
                        maxLength = LICENSE_PLATE_SIZE,
                        hasDividerTop = false,
                        hasDividerBottom = false
                    )

                    FilterChipGroupComponent(
                        options = TripStatusFilterEnum.entries.toTypedArray(),
                        selectedOption = selectedTripStatus,
                        getLabelRes = { it.label },
                        onOptionSelected = { selectedTripStatus = it }
                    )

                    DatePickerComponent(
                        onStartDateChange = { startDateState = it },
                        onEndDateChange = { endDateState = it },
                        hasDividerTop = false
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                item {
                    Spacer(modifier = Modifier.height(8._dph))
                }

                items(mockTrips) { trip ->
                    CardTripComponent(
                        trip = trip,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TripsContentPreview() {
    MeuCaminhaoTheme {
        TripsContent(TripsViewModel())
    }
}