package com.example.todo_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val addButton = findViewById<Button>(R.id.add)
        addButton.setOnClickListener {

            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        val deleteAll = findViewById<Button>(R.id.deleteAll)
        deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }
        setRecycler()
    }

    private fun setRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = CustomAdapter(DataObject.getAllData())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}