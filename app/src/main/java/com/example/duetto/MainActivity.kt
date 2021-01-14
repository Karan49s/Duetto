package com.example.duetto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val  transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame, TasksFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}