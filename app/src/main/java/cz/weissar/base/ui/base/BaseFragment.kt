package cz.weissar.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import cz.weissar.base.R
import cz.weissar.base.common.enums.State
import cz.weissar.base.di.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_base.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe {
            progressBar.isVisible = it is State.Loading

            if (it is State.Failure) {
                Snackbar.make(view, it.e.message ?: "Uknown failure", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    protected fun <T> LiveData<T>.observe(function: (value: T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer { function(it) })
    }
}