package com.example.duetto

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_task.*
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Locale.getDefault


class AddTask : AppCompatActivity() {

    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        newtaskdonebutton.setOnClickListener {
            if (newtaskname.text.isBlank()){
                Toast.makeText(this, "empty task", Toast.LENGTH_SHORT).show()
            }else{
                db.reference.child("users").child(auth.uid!!).child("tasks").push().setValue(newtaskname.text.toString())
                onBackPressed()
            }
        }
    }
}