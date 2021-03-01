package com.example.duetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*

lateinit var db: FirebaseDatabase
lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        if (auth.currentUser == null){
            auth.signInAnonymously().addOnCompleteListener {
                db.reference.child("users").child(auth.uid!!).child("tasks").child("new").setValue("new user")
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, "Signin error", Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(this@MainActivity, "Already Signed in"+ auth.uid, Toast.LENGTH_LONG).show()
        }

        addnewtaskfab.setOnClickListener {
            val intent = Intent(this@MainActivity,AddTask::class.java)
            startActivity(intent)
        }



        val  transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame, TasksFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}