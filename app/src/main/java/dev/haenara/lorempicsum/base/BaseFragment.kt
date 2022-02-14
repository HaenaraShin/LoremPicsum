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
import dev.haenara.lorempicsum.event.UiEvent
import dev.haenara.lorempicsum.event.UiEventListener

interface BaseView<B : ViewDataBinding> {
    val layoutId: Int
    var mBinding: B
    val viewModel: BaseViewModel
    fun initializeBinding(binding: B)
}

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes override val layoutId: Int,
) : Fragment(), BaseView<B> {
    override lateinit var mBinding: B
    private val uiEventListener: UiEventListener by lazy { UiEventListener(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        viewModel.uiEvent.observe(viewLifecycleOwner) {
            uiEventListener.onEventReceived(it)
        }
        initializeBinding(mBinding)
        return mBinding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.uiEvent.value = UiEvent.None
    }
}

abstract class BaseDialogFragment<B : ViewDataBinding>(
    @LayoutRes override val layoutId: Int,
) : DialogFragment(), BaseView<B> {
    override lateinit var mBinding: B
    private val uiEventListener: UiEventListener by lazy { UiEventListener(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        viewModel.uiEvent.observe(viewLifecycleOwner) {
            uiEventListener.onEventReceived(it)
        }
        initializeBinding(mBinding)
        return mBinding.root
    }
}
