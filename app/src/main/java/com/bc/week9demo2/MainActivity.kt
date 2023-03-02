package com.bc.week9demo2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<Any>()
        list.addAll(IntRange(0,10))
        list.add(Color.valueOf(0.1f, 0.2f, 0.3f))
        list.addAll(IntRange(11,20))
        list.add(Color.valueOf(0.4f, 0.1f, 0.3f))
        list.addAll(IntRange(21,30))

        val rv = findViewById<RecyclerView>(R.id.rv)
        val manager = GridLayoutManager(this, 2)

        manager.spanSizeLookup = object:GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(list[position]){
                    is Int->1
                    else ->2
                }
            }
        }

        //rv.layoutManager = LinearLayoutManager(this)
        rv.layoutManager = manager

        //val adapter = NumberListAdapter()
        val adapter = NumberListAdapter2()
        adapter.setData(list)

        rv.adapter = adapter

        findViewById<Button>(R.id.sortbutton).setOnClickListener {
            //adapter.setData(list.sorted())
        }

    }
}


@BindingAdapter("base2Number")
fun TextView.setBase2Number(item: Int) {
    text = Integer.toBinaryString(item)
}
