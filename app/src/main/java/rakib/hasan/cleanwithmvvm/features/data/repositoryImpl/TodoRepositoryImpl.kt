package rakib.hasan.cleanwithmvvm.features.data.repositoryImpl

import rakib.hasan.cleanwithmvvm.core.base.BaseResult
import rakib.hasan.cleanwithmvvm.core.base.Failure
import rakib.hasan.cleanwithmvvm.features.data.local.TodoDao
import rakib.hasan.cleanwithmvvm.features.data.remote.TodoRemoteSource
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity
import rakib.hasan.cleanwithmvvm.features.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepositoryImpl constructor(
    private val todoRemoteSource: TodoRemoteSource,
    private val todoDao: TodoDao
) : TodoRepository {
    override suspend fun fetchTodos(): Flow<BaseResult<List<TodoEntity>, Failure>> {
        return flow {
            val localTodos = todoDao.findAll()

            // send the local db value to db
            emit(BaseResult.Success(localTodos))

            // is should fetch?
            if (localTodos.isNullOrEmpty()) {
                val result = todoRemoteSource.fetchTodos()
                if (result is BaseResult.Success) {
                    saveToLocal(result.data)
                }
                emit(result)
            }
        }
    }

    private fun saveToLocal(todos: List<TodoEntity>) {
        todoDao.deleteAll()
        todoDao.insertAll(todos)
    }
}