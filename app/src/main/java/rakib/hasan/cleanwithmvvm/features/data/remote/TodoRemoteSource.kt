package rakib.hasan.cleanwithmvvm.features.data.remote

import rakib.hasan.cleanwithmvvm.core.base.BaseResult
import rakib.hasan.cleanwithmvvm.core.base.Failure
import rakib.hasan.cleanwithmvvm.core.exception.NoInternetConnectionException
import rakib.hasan.cleanwithmvvm.features.data.remote.api.TodoApi
import rakib.hasan.cleanwithmvvm.features.domain.entity.TodoEntity

class TodoRemoteSource constructor(private val todoApi: TodoApi) {
    suspend fun fetchTodos(): BaseResult<List<TodoEntity>, Failure> {
        try {
            val response = todoApi.todos()
            return if (response.isSuccessful) {
                val todos = mutableListOf<TodoEntity>()
                response.body()?.forEach { todo ->
                    todos.add(TodoEntity(todo.id, todo.title, todo.isCompleted))
                }
                BaseResult.Success(todos)
            } else {
                //since we don't know the error response of jsonplacheholder.com,
                //i will create a basic error here
                BaseResult.Error(Failure(response.code(), response.message()))
            }
        } catch (e: NoInternetConnectionException) {
            return BaseResult.Error(Failure(0, e.message))
        } catch (e: Exception) {
            return BaseResult.Error(Failure(-1, e.message.toString()))
        }
    }
}