package com.dys.mobile.uikit.components.enum

import com.dys.mobile.uikit.R

enum class ProfileTypeInfoEnum(val items: List<Pair<Int, Int>>) {
    ALPHA(
        listOf(
            R.drawable.ic_truck to R.string.text_vehicle_management,
            R.drawable.ic_chart_bars to R.string.text_financial_reports_access,
            R.drawable.ic_steering_wheel to R.string.text_driver_and_trip_monitoring,
            R.drawable.ic_checklist to R.string.text_custom_checklist_management,
        )
    )
}