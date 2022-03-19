package cz.weissar.base.ui.dummy

import cz.weissar.base.databinding.FragmentDummyBinding
import cz.weissar.base.ui.base.BaseFragment
import cz.weissar.base.ui.base.Inflater
import org.koin.androidx.viewmodel.ext.android.viewModel

class DummyFragment : BaseFragment<FragmentDummyBinding>() {

    override val viewModel by viewModel<DummyViewModel>()

    private val adapter by lazy {
        DummyAdapterBuilder(requireContext()) {
            // on click
        }.adapter
    }

    override fun FragmentDummyBinding.initViews() {
        recyclerView.adapter = adapter
        viewModel.getOrLoadDummy() // Call method
    }

    override fun FragmentDummyBinding.initObservers() {
        viewModel.dummyData.observe {
            adapter.submitList(it)
        }
    }

    override val inflater: Inflater<FragmentDummyBinding>
        get() = FragmentDummyBinding::inflate
}