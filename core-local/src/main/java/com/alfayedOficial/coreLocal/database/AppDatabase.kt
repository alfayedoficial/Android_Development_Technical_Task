package com.alfayedOficial.coreLocal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfayedOficial.coreLocal.dao.MovieDao
import com.alfayedOficial.coreLocal.model.MovieEntity

@Database(
    version = 1,
    entities = [
        MovieEntity::class
    ],
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
