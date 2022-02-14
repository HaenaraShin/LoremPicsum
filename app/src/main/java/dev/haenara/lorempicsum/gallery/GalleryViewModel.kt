package dev.haenara.lorempicsum.gallery

import dev.haenara.lorempicsum.base.BaseViewModel
import dev.haenara.lorempicsum.event.UiEvent

class GalleryViewModel : BaseViewModel() {
    fun close() {
        uiEvent.value = UiEvent.Back
    }
}
