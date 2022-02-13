package dev.haenara.lorempicsum.base

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object BaseViewModelFactory {
    fun <VM : ViewModel> baseViewModelFactory(
        creator: () -> VM
    ): (() -> ViewModelProvider.Factory) {
        return {
            object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return creator() as T
                }
            }
        }
    }
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.baseViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline viewModelProducer: () -> VM
) = viewModels<VM>(
    ownerProducer,
    BaseViewModelFactory.baseViewModelFactory(viewModelProducer)
)
