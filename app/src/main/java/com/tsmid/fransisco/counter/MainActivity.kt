package com.tsmid.fransisco.counter

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    private var counter2 : Int = 0

    var db: AppDatabase? = null
    private val b = Counter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(this, AppDatabase::class.java, "orderdb").allowMainThreadQueries().build()

        counter2 = db?.orderDAO()?.selectCounter()!!
        Log.i(TAG, "db -> $counter2")
        counter.text = "$counter2"

        switch2.onSwipedOnListener = {
            counter2 = counter2.plus(1)
            b.counterId = counter2
            time.text = setTime()
            time.visibility = View.VISIBLE
            db?.orderDAO()?.insertCounter(b)
            Log.d(TAG, "onSwipedOn")
            counter.text = "$counter2"
        }

        switch2.onSwipedOffListener = {
            counter2 = counter2.plus(1)
            b.counterId = counter2
            time.text = setTime()
            db?.orderDAO()?.insertCounter(b)
            time.text = setTime()
            Log.d(TAG, "onSwipedOff")
            counter.text = "$counter2"
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun setTime() : String{
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("HH:mm")
        return "Started at " + mdformat.format(calendar.time)
    }
}
