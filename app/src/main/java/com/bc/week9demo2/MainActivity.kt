package com.bc.week9demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = IntRange(0, 100).toList().shuffled()

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)


        //val adapter = NumberListAdapter()
        val adapter = NumberListAdapter2()
        adapter.setData(list)

        rv.adapter = adapter

        findViewById<Button>(R.id.sortbutton).setOnClickListener {
            adapter.setData(list.sorted())
        }

    }
}