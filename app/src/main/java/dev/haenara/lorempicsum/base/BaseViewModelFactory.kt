package dev.haenara.lorempicsum.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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
