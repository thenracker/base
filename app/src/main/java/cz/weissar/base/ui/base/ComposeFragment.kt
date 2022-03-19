package cz.weissar.base.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cz.weissar.base.databinding.FragmentComposeBinding

abstract class ComposeFragment : BaseFragment<FragmentComposeBinding>() {

    @SuppressLint("ComposableNaming")
    @Composable
    abstract fun setContent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setContent {
            BaseStateScreen(state = viewModel.state.collectAsState().value) {
                setContent()
            }
        }
    }

    override val inflater: Inflater<FragmentComposeBinding>
        get() = FragmentComposeBinding::inflate
}

