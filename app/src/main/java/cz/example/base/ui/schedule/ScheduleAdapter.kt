package cz.example.base.ui.schedule

import android.content.Context
import android.view.View
import cz.example.base.R
import cz.example.base.data.rest.dto.CalendarScheduleItemResponse
import cz.example.base.ui.base.BaseListAdapterBuilder

class ScheduleAdapter(context: Context, private val onClick: (CalendarScheduleItemResponse) -> Unit) : BaseListAdapterBuilder<CalendarScheduleItemResponse>(context) {

    override fun getRowLayout() = R.layout.row_schedule

    override fun View.onBind(item: CalendarScheduleItemResponse) {
        // ToDo bind

        setOnClickListener {
            onClick(item)
        }
    }
}