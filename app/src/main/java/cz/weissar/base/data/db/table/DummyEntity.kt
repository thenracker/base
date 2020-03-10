package cz.weissar.base.data.db.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(/*primaryKeys = ["sloupec1"]*/)
data class DummyEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    val note: String?
)
