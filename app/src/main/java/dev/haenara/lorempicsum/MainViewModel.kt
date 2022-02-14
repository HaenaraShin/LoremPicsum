package dev.haenara.lorempicsum

import androidx.lifecycle.MutableLiveData
import dev.haenara.lorempicsum.base.BaseViewModel
import dev.haenara.lorempicsum.domain.LoadImageUseCase
import dev.haenara.lorempicsum.domain.data.Image
import dev.haenara.lorempicsum.domain.data.Images
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(
    override var lastRequest: LoadImageUseCase.Request?,
    override val api: (LoadImageUseCase.Request) -> Single<Images>
) : BaseViewModel(), LoadImageUseCase {
    val result = MutableLiveData<LoadImageUseCase.Result>()

    private lateinit var _scrollObservable: Observable<Unit>
    override val scrollObservable: Observable<Unit> by lazy { _scrollObservable }

    private val disposableBag = mutableListOf<Disposable>()

    fun showDetailImage(image: Image) {
        navigate(MainFragmentDirections.actionMainFragmentToGalleryFragment(image))
    }

    override fun notifyUpdate(result: LoadImageUseCase.Result) {
        this.result.postValue(result)
    }

    fun setScrollObservable(observable: Observable<Int>) {
        _scrollObservable = observable.map { }
        loadImageObservable.subscribe(
            {},
            { e ->
                e.printStackTrace()
            }
        ).also {
            disposableBag.add(it)
        }
    }

    fun disposeAll() {
        disposableBag.forEach {
            it.dispose()
        }
    }
}
