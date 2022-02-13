package dev.haenara.lorempicsum.splash

import dev.haenara.lorempicsum.base.BaseViewModel
import dev.haenara.lorempicsum.domain.SplashUseCase
import io.reactivex.rxjava3.core.Single

class SplashViewModel(
    override val api: Single<List<Any>>
) : BaseViewModel(), SplashUseCase {
    init {
        runSplash()
    }

    override fun onError(exception: Throwable) {
        // TODO
    }

    override fun onSuccess(list: List<Any>) {
        navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }
}
