package com.datasoft.kotlintemplate.features.domain.repository

import com.datasoft.kotlintemplate.core.base.BaseResult
import com.datasoft.kotlintemplate.core.base.Failure
import com.datasoft.kotlintemplate.features.domain.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun fetchTodos() : Flow<BaseResult<List<TodoEntity>, Failure>>
}