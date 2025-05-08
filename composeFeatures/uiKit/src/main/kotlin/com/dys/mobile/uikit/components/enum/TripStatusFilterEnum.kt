package com.dys.mobile.uikit.components.enum

import androidx.annotation.StringRes
import com.dys.mobile.uikit.R

enum class TripStatusFilterEnum(@StringRes val label: Int) {
    ALL(R.string.text_status_all_trips),
    IN_PROGRESS(R.string.text_status_in_progress),
    FINISHED(R.string.text_status_finished),
    CANCELED(R.string.text_status_canceled)
}