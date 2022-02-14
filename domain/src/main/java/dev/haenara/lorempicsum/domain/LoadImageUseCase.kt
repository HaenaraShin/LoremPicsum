package dev.haenara.lorempicsum.domain

import dev.haenara.lorempicsum.domain.data.Image
import dev.haenara.lorempicsum.domain.data.Images
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.Serializable

interface LoadImageUseCase {
    val api: (Request) -> Single<Images>
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
    ) : Serializable

    companion object {
        const val LIMIT = 30
    }
}
