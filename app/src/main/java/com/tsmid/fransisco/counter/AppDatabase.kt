package com.tsmid.fransisco.counter

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Counter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDAO(): OrderDao

}