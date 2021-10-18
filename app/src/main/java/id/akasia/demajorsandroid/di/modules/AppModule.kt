package id.akasia.demajorsandroid.di.modules

import android.content.Context
import androidx.room.Room
import id.akasia.demajorsandroid.App
import id.akasia.demajorsandroid.data.AppDatabase
import id.akasia.demajorsandroid.data.AppDatabase.Companion.MIGRATION_1_2
import dagger.Provides
import id.akasia.demajorsandroid.di.scopes.ApplicationContext
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
            //.addMigrations(MIGRATION_1_2) //TODO enable this to test migration from v1 to v2
            .build()
    }

}