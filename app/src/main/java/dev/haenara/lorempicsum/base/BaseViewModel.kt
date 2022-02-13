package dev.haenara.lorempicsum.base

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dev.haenara.lorempicsum.event.UiEvent

abstract class BaseViewModel : ViewModel() {
    val uiEvent: MutableLiveData<UiEvent> = MutableLiveData(UiEvent.None)

    fun navigate(event: NavDirections) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            uiEvent.value = UiEvent.Navigation(event)
        } else {
            uiEvent.postValue(UiEvent.Navigation(event))
        }
    }
}
