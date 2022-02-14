package dev.haenara.lorempicsum.gallery

import android.view.View
import androidx.navigation.fragment.navArgs
import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.base.BaseDialogFragment
import dev.haenara.lorempicsum.base.baseViewModels
import dev.haenara.lorempicsum.databinding.GalleryFragmentBinding
import dev.haenara.lorempicsum.util.ui.setPhoto

class GalleryFragment : BaseDialogFragment<GalleryFragmentBinding>(R.layout.gallery_fragment) {
    private val args: GalleryFragmentArgs by navArgs()
    override val viewModel: GalleryViewModel by baseViewModels {
        GalleryViewModel()
    }

    override fun initializeBinding(binding: GalleryFragmentBinding) {
        binding.ivGallery.setPhoto(args.image)
        with(binding) {
            photo = args.image
            close = View.OnClickListener { _ -> dismiss() }
        }
    }
}
