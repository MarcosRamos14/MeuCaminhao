package com.dys.mobile.vehicles.ui.newVehicle

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.TakePicturePreview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.navigation.routes.Routes.FullPhotoScreen
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleErrorState
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.BrandChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.ChecklistsChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.CreateNewVehicle
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.DriversChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.IsOpenSelectorPhoto
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.ModelChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.PhotoUriChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.PlateChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleState
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.extensions.toUri
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.bottomSheet.CommonBottomSheet
import com.dys.mobile.uikit.components.bottomSheet.PhotoSourceBottomSheet
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.dropdown.DropdownItem
import com.dys.mobile.uikit.components.dropdown.MultiSelectDropdownComponent
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewVehicleScreen(navController: NavController) {
    val viewModel = koinViewModel<NewVehicleViewModel>()
    val newVehicleState = viewModel.newVehicleState.collectAsState().value

    NewVehicleContent(
        navController = navController,
        state = newVehicleState,
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun NewVehicleContent(
    navController: NavController,
    state: NewVehicleState,
    event: (Event) -> Unit
) {
    val context = LocalContext.current

    val invalidPlateError  = state.error is NewVehicleErrorState.InvalidPlate
    val emptyPlateError = state.error is NewVehicleErrorState.EmptyPlate
    val emptyBrandError = state.error is NewVehicleErrorState.EmptyBrand
    val emptyModelError = state.error is NewVehicleErrorState.EmptyModel

    val handlePhotoResult: (Uri) -> Unit = { uri ->
        event(PhotoUriChanged(uri.toString()))
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = PickVisualMedia(),
        onResult = { uri ->
            uri?.let { handlePhotoResult(it) }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let {
                handlePhotoResult(it.toUri(context))
            }
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(title = stringResource(R.string.text_new_vehicle))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16._dpw)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_plate),
                isRequired = true,
                placeHolder = stringResource(R.string.text_typing),
                value = state.plate.uppercase(),
                isError = invalidPlateError || emptyPlateError,
                errorMessage = when {
                    emptyPlateError -> R.string.common_required_field
                    invalidPlateError -> R.string.text_invalid_plate
                    else -> null
                },
                onValueChange = { newValue ->
                    if (newValue.length <= 7) event(PlateChanged(newValue))
                },
            )

            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_make),
                isRequired = true,
                placeHolder = stringResource(R.string.text_typing),
                value = state.brand,
                isError = emptyBrandError,
                errorMessage = if (emptyBrandError) R.string.common_required_field else null,
                onValueChange = { event(BrandChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_model),
                isRequired = true,
                placeHolder = stringResource(R.string.text_typing),
                value = state.model,
                isError = emptyModelError,
                errorMessage = if (emptyModelError) R.string.common_required_field else null,
                onValueChange = { event(ModelChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            TextComponent(
                text = stringResource(R.string.text_vehicle_photo),
                color = Black,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8._dph))

            val photoUrl = state.photoUri

            ImageComponent(
                modifier = Modifier.height(120._dph),
                url = photoUrl ?: "",
                contentDescription = R.string.text_vehicle_photo,
                shape = Shapes.large,
                fallback = R.drawable.ic_camera_default,
                openImageClick = {
                   if (!photoUrl.isNullOrEmpty()) {
                       event(
                           NavigateTo(
                               FullPhotoScreen.routeWithArgs(Uri.encode(photoUrl))
                           )
                       )
                   }
                }
            )

            Spacer(modifier = Modifier.height(8._dph))

            OutlinedRoundButtonComponent(
                text = stringResource(
                    if (state.photoUri.isNullOrEmpty()) R.string.text_add_photo else R.string.text_change_photo
                ),
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                onClick = {
                    event(IsOpenSelectorPhoto(true))
                }
            )

            Spacer(modifier = Modifier.height(16._dph))

            val drivers = state.drivers.orEmpty()

            if (drivers.isNotEmpty()) {
                MultiSelectDropdownComponent(
                    title = R.string.text_link_drivers,
                    items = drivers.map {
                        DropdownItem(
                            id = it.id,
                            title = it.name
                        )
                    },
                    onSelectionChange = { dropdownItems ->
                        dropdownItems
                            .mapNotNull { dropdown -> drivers.find { it.id == dropdown.id } }
                            .takeIf { it.isNotEmpty() }
                            ?.let { event(DriversChanged(it)) }
                    }
                )

                Spacer(modifier = Modifier.height(16._dph))
            }

            val checklists = state.checklists.orEmpty()

            if (checklists.isNotEmpty()) {
                MultiSelectDropdownComponent(
                    title = R.string.text_link_checklists,
                    items = checklists.map {
                        DropdownItem(
                            id = it.id,
                            title = it.name
                        )
                    },
                    onSelectionChange = { dropdownItems ->
                        dropdownItems
                            .mapNotNull { dropdown -> checklists.find { it.id == dropdown.id } }
                            .takeIf { it.isNotEmpty() }
                            ?.let { event(ChecklistsChanged(it)) }
                    }
                )

                Spacer(modifier = Modifier.height(16._dph))
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8._dpw)
            ) {
                OutlinedRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.common_text_cancel),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    onClick = { navController.popBackStack() }
                )

                FilledRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.text_save_vehicle),
                    onClick = { event(CreateNewVehicle) }
                )
            }

            Spacer(modifier = Modifier.height(16._dph))
        }
    }

    if (state.isOpenFeedbackBottomSheet) {
        CommonBottomSheet(
            icon = R.drawable.thumbnail_check_circle,
            title = R.string.text_vehicle_added_to_the_fleet,
            message = R.string.text_vehicle_was_successfully_added,
            textPositiveButton = R.string.common_text_ok,
            onPositiveButtonClicked = { navController.popBackStack() },
            onDismissRequest = { navController.popBackStack() }
        )
    }

    if (state.isOpenPhotoSourceBottomSheet) {
        PhotoSourceBottomSheet(
            onTakePhotoClicked = {
                cameraLauncher.launch(null)
            },
            onChooseFromGalleryClicked = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(PickVisualMedia.ImageOnly)
                )
            },
            onDismissRequest = { event(IsOpenSelectorPhoto(false)) }
        )
    }

    if (state.error != null) {
        // TODO: Show toast
    }
}

@Preview
@Composable
private fun NewVehiclePreview() {
    MeuCaminhaoTheme {
        NewVehicleContent(
            navController = NavController(LocalContext.current),
            state = NewVehicleState(),
            event = { }
        )
    }
}