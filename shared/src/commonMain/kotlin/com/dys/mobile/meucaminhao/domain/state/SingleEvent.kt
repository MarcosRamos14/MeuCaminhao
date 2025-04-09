package com.dys.mobile.meucaminhao.domain.state

open class SingleEvent<out T>(private val content: T) {

    private var hasBeenHandled = false

    val valueOrNull: T?
        get() = content
            .takeIf { !hasBeenHandled }
            ?.also { hasBeenHandled = true }

    fun peek(): T = content
}

fun <T> T.asSingleEvent() = SingleEvent(this)