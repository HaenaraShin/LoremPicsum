package dev.haenara.lorempicsum.event

import androidx.annotation.StringRes
import androidx.navigation.NavDirections

sealed class UiEvent {
    object None : UiEvent()
    object Back : UiEvent()
    data class Toast(val stringOrRes: StringOrRes) : UiEvent()
    data class Dialog(val stringOrRes: StringOrRes, val button: DialogButton) : UiEvent()
    open class Navigation(val destination: NavDirections) : UiEvent()
}

data class DialogButton(
    val text: StringOrRes,
    val onClick: (() -> Unit) = {}
) {
    constructor(
        text: String,
        onClick: (() -> Unit)
    ) : this(StringOrRes(text), onClick)

    constructor(
        @StringRes text: Int,
        onClick: (() -> Unit)
    ) : this(StringOrRes(text), onClick)
}
