package rakib.hasan.cleanwithmvvm.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import rakib.hasan.cleanwithmvvm.features.data.local.TodoDao
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}