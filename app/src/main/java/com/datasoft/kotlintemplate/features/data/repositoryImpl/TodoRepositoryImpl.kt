package com.datasoft.kotlintemplate.features.data.repositoryImpl

import com.datasoft.kotlintemplate.core.base.BaseResult
import com.datasoft.kotlintemplate.core.base.Failure
import com.datasoft.kotlintemplate.features.data.local.TodoDao
import com.datasoft.kotlintemplate.features.data.remote.TodoRemoteSource
import com.datasoft.kotlintemplate.features.domain.entity.TodoEntity
import com.datasoft.kotlintemplate.features.domain.repository.TodoRepository
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