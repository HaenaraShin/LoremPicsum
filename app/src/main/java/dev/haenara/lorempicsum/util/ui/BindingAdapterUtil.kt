package dev.haenara.lorempicsum.util.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.haenara.lorempicsum.domain.data.Image

@BindingAdapter(value = ["app:photo"])
fun ImageView.setPhoto(image: Image) {
    Glide.with(context)
        .load(image.downloadUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}
