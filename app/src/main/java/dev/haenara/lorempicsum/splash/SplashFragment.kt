package dev.haenara.lorempicsum.splash

import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.SplashFragmentBinding
import dev.haenara.lorempicsum.io.ApiClient
import dev.haenara.lorempicsum.main.ImageLoadRepo

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {
    override val viewModel: SplashViewModel by baseViewModels {
        SplashViewModel(
            repo.loadImages(1)
        )
    }

    private val repo = ImageLoadRepo(ApiClient.api)

    override fun initializeBinding(binding: SplashFragmentBinding) {}
}
