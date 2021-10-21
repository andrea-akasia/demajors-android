package com.demajors.demajorsapp.di.modules

import android.content.Context
import androidx.room.Room
import com.demajors.demajorsapp.App
import com.demajors.demajorsapp.data.AppDatabase
import dagger.Provides
import com.demajors.demajorsapp.di.scopes.ApplicationContext
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @ApplicationContext
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(application: App): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "demajors.db")
            // .addMigrations(MIGRATION_1_2) //TODO enable this to test migration from v1 to v2
            .build()
    }
}
