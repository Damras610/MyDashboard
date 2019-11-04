package com.example.mydashboard.di

import android.content.Context
import androidx.room.Room
import com.example.mydashboard.model.AppDatabase
import com.example.mydashboard.model.user.UserDao
import com.example.mydashboard.model.user.UserRepository
import com.example.mydashboard.model.widget.WidgetDao
import com.example.mydashboard.model.widget.WidgetRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Dashboard.db")
            .allowMainThreadQueries() // TODO To remove
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) : UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideWidgetDao(appDatabase: AppDatabase) : WidgetDao {
        return appDatabase.widgetDao()
    }

    @Singleton
    @Provides
    fun provideWidgetRepository(userDao: UserDao, widgetDao: WidgetDao) : WidgetRepository {
        return WidgetRepository(userDao, widgetDao)
    }

}