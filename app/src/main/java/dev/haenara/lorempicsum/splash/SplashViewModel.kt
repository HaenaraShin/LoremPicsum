package dev.haenara.lorempicsum.splash

import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseViewModel
import dev.haenara.lorempicsum.domain.SplashUseCase
import dev.haenara.lorempicsum.domain.data.Images
import dev.haenara.lorempicsum.event.DialogButton
import dev.haenara.lorempicsum.event.StringOrRes
import dev.haenara.lorempicsum.event.UiEvent
import io.reactivex.rxjava3.core.Single

class SplashViewModel(
    override val api: Single<Images>
) : BaseViewModel(), SplashUseCase {
    init {
        runSplash()
    }

    override fun onError(exception: Throwable) {
        uiEvent.postValue(
            UiEvent.Dialog(
                StringOrRes(R.string.error_splash),
                DialogButton(StringOrRes(R.string.exit)) {
                    uiEvent.value = UiEvent.Back
                }
            )
        )
    }

    override fun onSuccess(list: Images) {
        navigate(
            SplashFragmentDirections.actionSplashFragmentToMainFragment(list)
        )
    }
}
