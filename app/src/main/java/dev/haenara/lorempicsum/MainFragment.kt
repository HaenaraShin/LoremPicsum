package dev.haenara.lorempicsum

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.databinding.MainFragmentBinding

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    override val viewModel: MainViewModel by viewModels()
    override fun initializeBinding(binding: MainFragmentBinding) {
        binding.tv.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_galleryFragment)
        }
    }
}
