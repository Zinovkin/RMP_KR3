package com.example.zinovkinkr3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class OrderList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val listView = findViewById<ListView>(R.id.listViewOrders)

        val stringData = intent.getStringArrayListExtra("orders")!!

        listView.adapter=ArrayAdapter(this, android.R.layout.simple_list_item_1, stringData.toArray())
    }
}