package cz.weissar.base.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import cz.weissar.base.data.db.table.DummyEntity

@Dao
interface DummyDao : BaseDao<DummyEntity> {

    @Query("Select * From DummyEntity")
    suspend fun getAll(): List<DummyEntity>

    @Query("Select * From DummyEntity Where note Like :note")
    suspend fun getAllByNote(note: String): List<DummyEntity>

    @Query("Select * From DummyEntity Where id = :id")
    suspend fun getById(id: Int): DummyEntity?

    @Query("Select note From DummyEntity Where id = :id")
    suspend fun getNoteById(id: Int): String?

}