package com.dys.mobile.vehicles.ui.vehicleDetails

import androidx.annotation.StringRes
import com.dys.mobile.uikit.R

enum class VehicleDetailPagesEnum(@StringRes val label: Int) {
    GENERAL_INFO(R.string.common_general_information),
    TRAVEL_EXPENSES(R.string.text_travel_expenses),
    EXTRA_EXPENSES(R.string.text_extra_expenses),
    CHECKLIST_HISTORY(R.string.text_checklist_history)
}