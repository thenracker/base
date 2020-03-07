package cz.example.base.ui.schedule

import android.os.Bundle
import android.view.View
import cz.example.base.R
import cz.example.base.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {

    override val viewModel by viewModel<ScheduleViewModel>()
    //private val viewModel2 by sharedViewModel<ScheduleViewModel>()

    private val adapter by lazy {
        ScheduleAdapter(requireContext()) {
            // ToDO on click
        }.adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init views
        recyclerView.adapter = adapter

        // init observers
        viewModel.schedule.observe {
            adapter.submitList(it)
        }

        // load
        viewModel.loadSchedule()
    }
}