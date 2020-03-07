package cz.example.base.ui.schedule

import android.os.Bundle
import android.view.View
import android.widget.ListAdapter
import cz.example.base.R
import cz.example.base.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {

    override val viewModel by viewModel<ScheduleViewModel>()
    //private val viewModel2 by sharedViewModel<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.schedule.observe {

            //recyclerView.adapter =
        }

        viewModel.loadSchedule()
    }
}