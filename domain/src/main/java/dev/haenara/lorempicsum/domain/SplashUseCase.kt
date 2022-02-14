package dev.haenara.lorempicsum.domain

import dev.haenara.lorempicsum.domain.data.Images
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

interface SplashUseCase {
    val api: Single<Images>
    fun runSplash() {
        val delay = Single.just(Unit)
            .delay(MINIMUM_DELAY, TimeUnit.MILLISECONDS)

        api.zipWith(delay) { output, _ -> output }
            .subscribe(
                { list ->
                    onSuccess(list)
                },
                { e ->
                    onError(e)
                }
            )
    }

    fun onError(exception: Throwable)

    fun onSuccess(list: Images)

    companion object {
        private const val MINIMUM_DELAY = 1000L
    }
}
