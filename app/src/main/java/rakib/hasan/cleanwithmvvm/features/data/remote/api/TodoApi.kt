package rakib.hasan.cleanwithmvvm.features.data.remote.api

import rakib.hasan.cleanwithmvvm.features.data.remote.dto.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun todos() : Response<List<TodoResponse>>
}