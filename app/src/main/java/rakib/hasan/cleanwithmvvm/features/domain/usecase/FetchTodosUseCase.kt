package rakib.hasan.cleanwithmvvm.features.domain.usecase

import rakib.hasan.cleanwithmvvm.core.base.BaseResult
import rakib.hasan.cleanwithmvvm.core.base.Failure
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity
import rakib.hasan.cleanwithmvvm.features.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTodosUseCase @Inject constructor (private val todoRepository: TodoRepository){
    suspend fun invoke(): Flow<BaseResult<List<TodoEntity>, Failure>> {
        return todoRepository.fetchTodos()
    }
}