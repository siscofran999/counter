package com.tsmid.fransisco.counter

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface OrderDao {

    @Insert
    fun insertCounter(vararg counter: Counter)

    @Query("SELECT COUNT(*) FROM tbCounter")
    fun selectCounter() : Int

}