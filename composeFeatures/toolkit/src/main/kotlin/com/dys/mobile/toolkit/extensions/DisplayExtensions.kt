package com.dys.mobile.toolkit.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.dys.mobile.toolkit.config.DisplayConfig

const val width: Float = 375f
const val height: Float = 812f

fun Float.toWidth() : Float {
    if (DisplayConfig.widthPixels == 1)  {
        return this
    }
    val screen: Int = DisplayConfig.widthPixels
    val current: Float = this
    val calc: Float = (screen * ((current / width))) / DisplayConfig.density
    return calc
}

fun Float.toHeight() : Float {
    if (DisplayConfig.heightPixels == 1)  {
        return this
    }
    val screen: Int = DisplayConfig.heightPixels
    val current: Float = this
    val calc: Float = (screen * ((current / height))) / DisplayConfig.density
    return calc
}

fun Float.toSize() : Float {
    if (DisplayConfig.heightPixels == 1)  {
        return this
    }
    val screen: Int = DisplayConfig.heightPixels
    val current: Float = this
    val calc: Float = (screen * (current / height))  / DisplayConfig.fontDensity
    return calc
}

val Int._dpw: Dp get() = if (this == 0) Dp(0f) else Dp(this.toFloat().toWidth())
val Int._dph: Dp get() = if (this == 0) Dp(0f) else Dp(this.toFloat().toHeight())
val Int._sp: TextUnit get() = if (this == 0) TextUnit(0f, TextUnitType.Sp) else TextUnit(this.toFloat().toSize(), TextUnitType.Sp)