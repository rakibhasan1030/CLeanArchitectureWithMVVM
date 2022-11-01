package rakib.hasan.cleanwithmvvm.features.domain.repository

import rakib.hasan.cleanwithmvvm.core.base.BaseResult
import rakib.hasan.cleanwithmvvm.core.base.Failure
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun fetchTodos() : Flow<BaseResult<List<TodoEntity>, Failure>>
}