package dev.haenara.lorempicsum.image

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.MainFragmentBinding
import dev.haenara.lorempicsum.domain.LoadImageUseCase
import dev.haenara.lorempicsum.io.ApiClient
import dev.haenara.lorempicsum.util.ui.addScrollObservable

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    private val args: MainFragmentArgs by navArgs()
    override val viewModel: MainViewModel by baseViewModels {
        MainViewModel(
            lastRequest = LoadImageUseCase.Request(1, LoadImageUseCase.LIMIT),
        ) { request ->
            ImageLoadRepo(ApiClient.api).loadImages(request.pageCount, request.limit)
        }
    }

    private val adapter: ImageListAdapter by lazy {
        ImageListAdapter(args.images) { image ->
            viewModel.showDetailImage(image)
        }
    }

    override fun initializeBinding(binding: MainFragmentBinding) {
        with(binding) {

            rvImageList.adapter = adapter
            rvImageList.layoutManager = LinearLayoutManager(requireContext())
            rvImageList.addScrollObservable(VISIBLE_THRESHOLD).also { observable ->
                viewModel.setScrollObservable(observable)
            }
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            adapter.updateImage(result.Images)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disposeAll()
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 4
    }
}
