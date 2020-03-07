package cz.example.base.data.rest.dto

import com.google.gson.annotations.SerializedName

data class CalendarScheduleItemResponse(

    @SerializedName("datumOd")
    val dateFrom: ValueContainer?,

    @SerializedName("datumDo")
    val dateTo: ValueContainer?,

    @SerializedName("popis")
    val description: String?
) {
    /*fun toScheduleEntity() = ScheduleEntity(
        dateFrom = dateFrom?.value,
        dateTo = dateTo?.value,
        description = description
    )*/
}