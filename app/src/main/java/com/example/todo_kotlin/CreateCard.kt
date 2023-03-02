package com.example.todo_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.util.Log


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java, "To_Do"
        ).build()
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            val createTask = findViewById<EditText>(R.id.create_task)
            val createPriority = findViewById<EditText>(R.id.create_priority)
            if (createTask.text.toString().trim{it <=' '}.isNotEmpty() && createPriority.text.toString().trim{it <=' '}.isNotEmpty())
            {

                DataObject.setData(createTask.text.toString() , createPriority.text.toString())
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,createTask.text.toString() , createPriority.text.toString()))
                }

            }

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}