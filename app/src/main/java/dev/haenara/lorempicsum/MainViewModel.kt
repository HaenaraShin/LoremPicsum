package dev.haenara.lorempicsum

import dev.haenara.lorempicsum.base.BaseViewModel
import dev.haenara.lorempicsum.domain.data.Image

class MainViewModel : BaseViewModel() {
    fun showDetailImage(image: Image) {
        navigate(MainFragmentDirections.actionMainFragmentToGalleryFragment(image))
    }
}
