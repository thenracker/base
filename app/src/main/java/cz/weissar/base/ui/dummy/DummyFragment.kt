package cz.weissar.base.ui.dummy

import android.os.Bundle
import android.view.View
import cz.weissar.base.databinding.FragmentDummyBinding
import cz.weissar.base.ui.base.BaseFragment
import cz.weissar.base.ui.base.Inflater
import kotlinx.android.synthetic.main.fragment_dummy.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DummyFragment : BaseFragment<FragmentDummyBinding>() {

    override val viewModel by viewModel<DummyViewModel>()

    private val adapter by lazy {
        DummyAdapterBuilder(requireContext()) {
            // ToDO on click
        }.adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init views
        recyclerView.adapter = adapter

        // init observers
        viewModel.dummyData.observe {
            adapter.submitList(it)
        }

        // load
        viewModel.getOrLoadDummy()
    }

    override val inflater: Inflater<FragmentDummyBinding>
        get() = FragmentDummyBinding::inflate
}