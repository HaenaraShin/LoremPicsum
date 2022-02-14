package dev.haenara.lorempicsum.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.haenara.lorempicsum.R
import dev.haenara.lorempicsum.databinding.ViewImageHolderBinding
import dev.haenara.lorempicsum.domain.data.Image
import dev.haenara.lorempicsum.util.log.LoreLog

class ImageListAdapter(
    private var imageList: List<Image>,
    private val onClickItem: (Image) -> Unit
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.view_image_holder, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            imageList[position]
        )
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun updateImage(list: List<Image>) {
        val oldList = imageList
        imageList = oldList + list
        notifyItemRangeInserted(oldList.size, list.size)
        LoreLog.v("updateImage :: newList : $imageList")
    }

    inner class ViewHolder(
        private val mBinding: ViewImageHolderBinding
    ) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: Image) {
            mBinding.photo = item
            mBinding.onClickListener = View.OnClickListener { _ ->
                onClickItem(item)
            }
        }
    }
}
