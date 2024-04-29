package com.example.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        FavEntity::class,
        HistoryEntity::class
    ],
    version = 4
)
abstract class Db : RoomDatabase() {
    abstract val favDao: FavDao
    abstract val historyDao: HistoryDao
    companion object{
        fun createDb(context: Context): Db{
            return Room.databaseBuilder(
                context,
                Db::class.java,
                "main.db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}