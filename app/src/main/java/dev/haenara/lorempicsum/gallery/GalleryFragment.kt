package dev.haenara.lorempicsum.gallery

import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseDialogFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.GalleryFragmentBinding

class GalleryFragment : BaseDialogFragment<GalleryFragmentBinding>(R.layout.gallery_fragment) {
    override val viewModel: GalleryViewModel by baseViewModels {
        GalleryViewModel()
    }

    override fun initializeBinding(binding: GalleryFragmentBinding) {}
}
