package dev.haenara.lorempicsum

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.databinding.MainFragmentBinding
import dev.haenara.lorempicsum.io.ApiClient
import dev.haenara.lorempicsum.main.ImageListAdapter

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    override val viewModel: MainViewModel by viewModels()

    private val adapter = ImageListAdapter(listOf()) { image ->
        viewModel.showDetailImage(image)
    }

    override fun initializeBinding(binding: MainFragmentBinding) {
        binding.rvImageList.adapter = adapter
        binding.rvImageList.layoutManager = LinearLayoutManager(requireContext())
        ApiClient.api.getRandomImages(1, 20).subscribe({ images ->
            requireActivity().runOnUiThread {
                adapter.updateImage(images)
            }
        },
            { e ->
                e.printStackTrace()
            }
        )
    }
}
