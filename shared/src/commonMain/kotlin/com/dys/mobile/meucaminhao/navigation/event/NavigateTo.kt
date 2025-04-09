package com.dys.mobile.meucaminhao.navigation.event

import com.dys.mobile.meucaminhao.domain.state.SingleEvent
import com.dys.mobile.meucaminhao.domain.state.asSingleEvent

data class NavigateTo(private val _route: String) : Event {
    val route: SingleEvent<String> = _route.asSingleEvent()
}