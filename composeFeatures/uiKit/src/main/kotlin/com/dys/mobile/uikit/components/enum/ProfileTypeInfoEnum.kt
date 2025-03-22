package com.dys.mobile.uikit.components.enum

import com.dys.mobile.meucaminhao.domain.model.ProfileTypeInfo
import com.dys.mobile.uikit.R

enum class ProfileTypeInfoEnum(val features: List<ProfileTypeInfo>) {
    ALPHA(
        listOf(
            ProfileTypeInfo(R.drawable.ic_truck, R.string.text_vehicle_management),
            ProfileTypeInfo(R.drawable.ic_chart_bars, R.string.text_financial_reports_access),
            ProfileTypeInfo(R.drawable.ic_steering_wheel, R.string.text_driver_and_trip_monitoring),
            ProfileTypeInfo(R.drawable.ic_checklist, R.string.text_custom_checklist_management),
        )
    )
}