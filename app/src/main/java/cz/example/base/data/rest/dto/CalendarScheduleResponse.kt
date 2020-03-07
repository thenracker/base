package cz.example.base.data.rest.dto

import com.google.gson.annotations.SerializedName

data class CalendarScheduleResponse(

    @SerializedName("harmonogramItem")
    val list: List<CalendarScheduleItemResponse>
)