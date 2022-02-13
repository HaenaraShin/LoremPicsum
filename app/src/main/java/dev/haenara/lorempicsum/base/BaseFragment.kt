package dev.haenara.lorempicsum.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

interface BaseView<B : ViewDataBinding> {
    val layoutId: Int
    var mBinding: B
    fun initializeBinding(binding: B)
}

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes override val layoutId: Int,
) : Fragment(), BaseView<B> {
    override lateinit var mBinding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        initializeBinding(mBinding)
        return mBinding.root
    }
}

abstract class BaseDialogFragment<B : ViewDataBinding>(
    @LayoutRes override val layoutId: Int,
) : DialogFragment(), BaseView<B> {
    override lateinit var mBinding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        initializeBinding(mBinding)
        return mBinding.root
    }
}
