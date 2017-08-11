package com.example.dmitry.quickbike.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import com.example.dmitry.quickbike.R
import com.example.dmitry.quickbike.architecture.vm.BaseViewModel

abstract class BaseFragment<T : BaseViewModel> : LifecycleFragment() {
    protected abstract val viewModelClass: Class<T>
    private lateinit var mProgressBar: ProgressBar
    protected val viewModel : T by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun createView(layoutRes: Int, inflater: LayoutInflater?, container: ViewGroup?) : View {
        val view = requireNotNull(inflater).inflate(R.layout.base_fragment_layout, container, false)
        initView(view, layoutRes)

        viewModel.isUpdatingLiveData
                .observe(this, Observer { isUpdating ->
                    isUpdating?.let { showProgress(isUpdating) }
                })
        return view
    }

    private fun showProgress(isUpdating: Boolean) {
        mProgressBar.visibility = if(isUpdating) View.VISIBLE else View.GONE
    }

    private fun initView(view: View, layoutRes: Int) {
        mProgressBar = view.findViewById(R.id.progress_bar) as ProgressBar
        val viewStub: ViewStub = view.findViewById(R.id.view_stub) as ViewStub
        viewStub.layoutResource = layoutRes
        viewStub.inflate()
    }
}
