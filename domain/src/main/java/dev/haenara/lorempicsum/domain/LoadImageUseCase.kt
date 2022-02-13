package dev.haenara.lorempicsum.domain

import dev.haenara.lorempicsum.domain.data.Image
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface LoadImageUseCase {
    val api: (Request) -> Single<List<Image>>
    val scrollObservable: Observable<Unit>

    var lastRequest: Request?
    val loadImageObservable: Observable<Result>
        get() = scrollObservable
            .map {
                Request(
                    pageCount = lastRequest?.pageCount?.inc() ?: 1
                )
            }
            .distinctUntilChanged()
            .flatMapSingle { request ->
                api(request)
                    .map { images ->
                        Result(request, images)
                    }
            }.doOnNext { result ->
                lastRequest = result.request
                notifyUpdate(result)
            }

    fun notifyUpdate(result: Result)

    data class Request(
        val pageCount: Int = 1,
        val limit: Int = LIMIT
    )

    data class Result(
        val request: Request,
        val Images: List<Image>
    )

    companion object {
        private const val LIMIT = 30
    }
}
