package com.application.absensi_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.application.absensi_app.data.model.Absensi

@Database(entities = [Absensi::class], version = 2, exportSchema = false)
abstract class AbsensiDatabase : RoomDatabase() {
    abstract fun getDao(): AbsensiDao

    companion object {
        @Volatile
        private var INSTANCE: AbsensiDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AbsensiDatabase {
            if (INSTANCE == null) {
                synchronized(AbsensiDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AbsensiDatabase::class.java, "absen")
                        .addMigrations(MIGRATION_1_2) // Menambahkan migrasi dari versi 1 ke versi 2
                        .build()
                }
            }
            return INSTANCE as AbsensiDatabase
        }
    }
}

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // empty migration.
    }
}