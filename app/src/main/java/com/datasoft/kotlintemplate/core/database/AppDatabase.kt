package com.datasoft.kotlintemplate.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datasoft.kotlintemplate.features.data.local.TodoDao
import com.datasoft.kotlintemplate.features.domain.entity.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}