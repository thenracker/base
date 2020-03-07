package cz.example.base.data.db.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<Entity>): List<Long>

    @Delete
    suspend fun delete(item: Entity)

    @Delete
    suspend fun deleteAll(items: List<Entity>)

    @Update
    suspend fun update(item: Entity)

    // Insert your class for runtime query in evaluate expression (instead of Any)
    // dao.rawQuery(SimpleSQLiteQuery("Select * From UserEntity"))
    @Deprecated("For Debug purpose only!")
    @RawQuery
    fun rawQuery(query: SupportSQLiteQuery): Entity // Replace Any with class you want to query to

    // dao.rawQueryList(SimpleSQLiteQuery("Select * From UserEntity"))
    @Deprecated("For Debug purpose only!")
    @RawQuery
    fun rawQueryList(query: SupportSQLiteQuery): List<Entity> // Replace Any with class you want to query to
}