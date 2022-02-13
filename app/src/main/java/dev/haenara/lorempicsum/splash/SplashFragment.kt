package dev.haenara.lorempicsum.splash

import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.SplashFragmentBinding
import io.reactivex.rxjava3.core.Single

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {
    override val viewModel: SplashViewModel by baseViewModels {
        SplashViewModel(
            Single.just(listOf())
        )
    }

    override fun initializeBinding(binding: SplashFragmentBinding) {}
}
