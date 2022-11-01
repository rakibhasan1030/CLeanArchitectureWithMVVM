package rakib.hasan.cleanwithmvvm.features.data.di

import rakib.hasan.cleanwithmvvm.core.database.AppDatabase
import rakib.hasan.cleanwithmvvm.features.data.local.TodoDao
import rakib.hasan.cleanwithmvvm.features.data.remote.TodoRemoteSource
import rakib.hasan.cleanwithmvvm.features.data.remote.api.TodoApi
import rakib.hasan.cleanwithmvvm.features.data.repositoryImpl.TodoRepositoryImpl
import rakib.hasan.cleanwithmvvm.features.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {
    @Provides
    @Singleton
    fun provideTodoRemoteApi(retrofit: Retrofit) : TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTodoRemoteSource(todoApi: TodoApi) : TodoRemoteSource {
        return TodoRemoteSource(todoApi)
    }

    @Provides
    @Singleton
    fun provideTodoDao(appDatabase: AppDatabase) : TodoDao {
        return appDatabase.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoRemoteSource: TodoRemoteSource, todoDao: TodoDao) : TodoRepository {
        return TodoRepositoryImpl(todoRemoteSource, todoDao)
    }

}