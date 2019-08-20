package com.tsmid.fransisco.counter

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("HH:mm")
        val times = "Started at " + mdformat.format(calendar.time)
        time.text = times

        counter2 = db?.orderDAO()?.selectCounter()!!
        Log.i(TAG, "db -> $counter2")
        counter.text = "$counter2"

        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                counter2 = counter2.plus(1)
                b.counterId = counter2
                Log.i(TAG, "true -> $counter2")
                db?.orderDAO()?.insertCounter(b)
                Log.i(TAG, "db2 -> ${db?.orderDAO()?.selectCounter()!!}")
                counter.text = "$counter2"
            }else{
                counter2 = counter2.plus(1)
                b.counterId = counter2
                Log.i(TAG, "false -> $counter2")
                db?.orderDAO()?.insertCounter(b)
                Log.i(TAG, "db2 -> ${db?.orderDAO()?.selectCounter()!!}")
                counter.text = "$counter2"
            }
        }
    }
}
