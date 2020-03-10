package cz.weissar.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.weissar.base.data.db.dao.DummyDao
import cz.weissar.base.data.db.table.DummyEntity


@Database(
    entities = [
        DummyEntity::class
    ],
    version = AppDatabase.Version
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val Version = 1
        const val Name = "ExampleDb"
    }

    abstract fun dummyDao(): DummyDao
}