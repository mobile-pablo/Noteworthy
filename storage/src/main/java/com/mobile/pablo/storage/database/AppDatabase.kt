package com.mobile.pablo.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobile.pablo.storage.database.converter.DateTypeConverter
import com.mobile.pablo.storage.database.dao.FullNoteDao
import com.mobile.pablo.storage.database.dao.PreviewNoteDao
import com.mobile.pablo.storage.database.entity.FullNoteEntity
import com.mobile.pablo.storage.database.entity.PreviewNoteEntity

@Database(
    entities = [
        FullNoteEntity::class,
        PreviewNoteEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTypeConverter::class
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun fullNoteDao(): FullNoteDao
    abstract fun previewNote(): PreviewNoteDao

    companion object {

        private const val DB_NAME = "app_database.db"
        private lateinit var instance: AppDatabase
        fun getInstance(context: Context): AppDatabase =
            if (this::instance.isInitialized) instance
            else
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .apply {
                        instance = this
                    }
    }
}