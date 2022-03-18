package cz.weissar.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import cz.weissar.base.common.enums.Failure
import cz.weissar.base.common.enums.Loading
import cz.weissar.base.databinding.FragmentBaseBinding
import cz.weissar.base.di.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

typealias Inflater<Binding> = (LayoutInflater, ViewGroup, Boolean) -> Binding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    abstract val viewModel: BaseViewModel
    abstract val inflater: Inflater<Binding>

    private var _baseBinding: FragmentBaseBinding? = null
    private var _binding: Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentBaseBinding.inflate(inflater, container, true)
        .also {
            _baseBinding = it
            _binding = inflater(inflater, it.container, true)
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe {
            progressBar.isVisible = it is Loading

            if (it is Failure) {
                Snackbar.make(view, it.exception.message.orEmpty(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _baseBinding = null
    }

    protected fun <T> LiveData<T>.observe(function: (value: T) -> Unit) {
        this.observe(viewLifecycleOwner) { function(it) }
    }

    protected fun <T> Flow<T>.observe(function: (value: T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            collect { function(it) }
        }
    }

    protected fun <T> Flow<T>.observeRepeatOnStart(function: (value: T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect { function(it) }
            }
        }
    }
}