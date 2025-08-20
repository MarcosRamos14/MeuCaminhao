package com.dys.mobile.vehicles.ui.editVehicle

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
import androidx.compose.runtime.LaunchedEffect
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
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.SaveChanges
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleState
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.IsOpenSelectorPhoto
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.PhotoUriChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.RequestVehicleForEdit
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.BrandChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.ChecklistsChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.DriversChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.ModelChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.PlateChanged
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
import kotlin.collections.find
import kotlin.collections.isNotEmpty
import kotlin.collections.map
import kotlin.collections.orEmpty

@Composable
fun EditVehicleScreen(id: Long, navController: NavController) {
    val viewModel = koinViewModel<EditVehicleViewModel>()
    val editVehicleState = viewModel.editVehicleState.collectAsState().value

    LaunchedEffect(id) {
        viewModel.onEvent(RequestVehicleForEdit(id))
    }

    EditVehicleContent(
        navController = navController,
        state = editVehicleState,
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun EditVehicleContent(
    navController: NavController,
    state: EditVehicleState,
    event: (Event) -> Unit
) {
    val context = LocalContext.current

    val invalidPlateError  = state.errors?.invalidPlate
    val emptyPlateError = state.errors?.emptyPlate
    val emptyBrandError = state.errors?.emptyBrand
    val emptyModelError = state.errors?.emptyModel

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
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_edit_vehicle)
            )
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
                isError = invalidPlateError != null || emptyPlateError != null,
                errorMessage = when {
                    emptyPlateError != null -> R.string.common_required_field
                    invalidPlateError != null -> R.string.text_invalid_plate
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
                isError = emptyBrandError != null,
                errorMessage = if (emptyBrandError != null) R.string.common_required_field else null,
                onValueChange = { event(BrandChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_model),
                isRequired = true,
                placeHolder = stringResource(R.string.text_typing),
                value = state.model,
                isError = emptyModelError != null,
                errorMessage = if (emptyModelError != null) R.string.common_required_field else null,
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

            val driversToLink = state.driversToLink.orEmpty()
            val linkedDrivers = state.linkedDrivers.orEmpty()

            if (driversToLink.isNotEmpty()) {
                MultiSelectDropdownComponent(
                    title = R.string.text_link_drivers,
                    items = driversToLink.map { driverToLink ->
                        DropdownItem(
                            id = driverToLink.id,
                            title = driverToLink.name,
                            isSelected = linkedDrivers.any { it.id == driverToLink.id }
                        )
                    },
                    onSelectionChange = { dropdownItems ->
                        val selectedDrivers = dropdownItems
                            .filter { it.isSelected }
                            .mapNotNull { dropdown ->
                                driversToLink.find { it.id == dropdown.id }
                            }

                        event(DriversChanged(selectedDrivers))
                    }
                )

                Spacer(modifier = Modifier.height(16._dph))
            }

            val checklistsToLink = state.checklistsToLink.orEmpty()
            val linkedChecklists = state.linkedChecklists.orEmpty()

            if (checklistsToLink.isNotEmpty()) {
                MultiSelectDropdownComponent(
                    title = R.string.text_link_checklists,
                    items = checklistsToLink.map { checklistToLink ->
                        DropdownItem(
                            id = checklistToLink.id,
                            title = checklistToLink.name,
                            isSelected = linkedChecklists.any { it.id == checklistToLink.id }
                        )
                    },
                    onSelectionChange = { dropdownItems ->
                        val selectedChecklists = dropdownItems
                            .filter { it.isSelected }
                            .mapNotNull { dropdown ->
                                checklistsToLink.find { it.id == dropdown.id }
                            }

                        event(ChecklistsChanged(selectedChecklists))
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
                    text = stringResource(R.string.text_edit),
                    onClick = { event(SaveChanges) }
                )
            }

            Spacer(modifier = Modifier.height(16._dph))
        }
    }

    if (state.isOpenFeedbackBottomSheet) {
        CommonBottomSheet(
            icon = R.drawable.thumbnail_check_circle,
            title = R.string.text_saved_changes,
            message = R.string.text_vehicle_changes_saved,
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

    if (state.errors != null) {
        // TODO: Show toast
    }
}

@Preview
@Composable
private fun EditVehiclePreview() {
    MeuCaminhaoTheme {
        EditVehicleContent(
            navController = NavController(LocalContext.current),
            state = EditVehicleState(),
            event = { }
        )
    }
}