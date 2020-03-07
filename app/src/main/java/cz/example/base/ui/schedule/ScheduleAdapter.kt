package cz.example.base.ui.schedule

import android.content.Context
import android.view.View
import cz.example.base.R
import cz.example.base.data.rest.dto.DummyResponse
import cz.example.base.ui.base.BaseListAdapterBuilder
import kotlinx.android.synthetic.main.row_schedule.view.*

class ScheduleAdapter(context: Context, private val onClick: (DummyResponse) -> Unit) :
    BaseListAdapterBuilder<DummyResponse>(context) {

    override fun getRowLayout() = R.layout.row_schedule

    override fun View.onBind(item: DummyResponse) {
        // ToDo bind
        name.text = item.title
        descripiton.text = item.body

        setOnClickListener {
            onClick(item)
        }
    }
}