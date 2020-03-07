package cz.example.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import cz.example.base.R
import cz.example.base.di.base.BaseViewModel

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment(R.layout.fragment_base) {

    abstract val viewModel : BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        //(view.container as FrameLayout)
        view?.findViewById<FrameLayout>(R.id.container).run {
            LayoutInflater.from(requireContext()).inflate(layoutId, this, true)
        }
        return view
    }

    protected fun <T> LiveData<T>.observe(function: (value: T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer { function(it) })
    }
}