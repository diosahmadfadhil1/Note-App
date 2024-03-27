package com.application.absensi_app.di

import android.content.Context
import androidx.room.Room
import com.application.absensi_app.data.database.AbsensiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun createInstanceRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AbsensiDatabase::class.java, "absensi").build()

    @Singleton
    @Provides
    fun provideDao(database: AbsensiDatabase) = database.getDao()

}