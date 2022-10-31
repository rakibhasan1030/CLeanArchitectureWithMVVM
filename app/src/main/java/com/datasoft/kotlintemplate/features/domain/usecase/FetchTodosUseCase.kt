package com.datasoft.kotlintemplate.features.domain.usecase

import com.datasoft.kotlintemplate.core.base.BaseResult
import com.datasoft.kotlintemplate.core.base.Failure
import com.datasoft.kotlintemplate.features.domain.entity.TodoEntity
import com.datasoft.kotlintemplate.features.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTodosUseCase @Inject constructor (private val todoRepository: TodoRepository){
    suspend fun invoke(): Flow<BaseResult<List<TodoEntity>, Failure>> {
        return todoRepository.fetchTodos()
    }
}