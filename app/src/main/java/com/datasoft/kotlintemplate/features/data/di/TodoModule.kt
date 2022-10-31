package com.datasoft.kotlintemplate.features.data.di

import com.datasoft.kotlintemplate.core.database.AppDatabase
import com.datasoft.kotlintemplate.features.data.local.TodoDao
import com.datasoft.kotlintemplate.features.data.remote.TodoRemoteSource
import com.datasoft.kotlintemplate.features.data.remote.api.TodoApi
import com.datasoft.kotlintemplate.features.data.repositoryImpl.TodoRepositoryImpl
import com.datasoft.kotlintemplate.features.domain.repository.TodoRepository
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