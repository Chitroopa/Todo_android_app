package com.example.todo_kotlin

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch




class UpdateCard : AppCompatActivity(){
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val createTask = findViewById<TextView>(R.id.create_task)
        val createPriority = findViewById<TextView>(R.id.create_priority)
        val updateButton = findViewById<Button>(R.id.update_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val pos = intent.getIntExtra("id", -1)

        if (pos != -1) {
.1
            createTask.text = DataObject.getData(pos).taskContent
            createPriority.text = DataObject.getData(pos).priority

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(pos + 1,
                            createTask.text.toString(),
                            createPriority.text.toString()
                        )
                    )
                }
                returnIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTask.text.toString(),
                    createPriority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, createTask.text.toString(),
                            createPriority.text.toString()
                        )
                    )
                }
                returnIntent()
            }
        }

    }
    private fun returnIntent() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}