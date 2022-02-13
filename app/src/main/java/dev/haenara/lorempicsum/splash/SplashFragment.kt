package dev.haenara.lorempicsum.splash

import androidx.navigation.fragment.findNavController
import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.SplashFragmentBinding

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {
    private val viewModel: SplashViewModel by baseViewModels {
        SplashViewModel()
    }

    override fun initializeBinding(binding: SplashFragmentBinding) {
        binding.ivLogo.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }
}
