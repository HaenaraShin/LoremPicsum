package dev.haenara.lorempicsum.base

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

@MainThread
inline fun <reified VM : ViewModel> Fragment.baseViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline viewModelProducer: () -> VM
) = viewModels<VM>(
    ownerProducer,
    BaseViewModelFactory.baseViewModelFactory(viewModelProducer)
)
