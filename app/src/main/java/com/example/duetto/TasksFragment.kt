package com.example.duetto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.*
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.android.synthetic.main.rsr_single_task.view.*


class TasksFragment : Fragment() {


    private  lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        var mytasksref = db.reference

        if(auth.uid != null) {
            mytasksref = db.reference.child("users").child(auth.uid!!).child("tasks")
        }



        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        recycler = view.findViewById(R.id.taskrecycler)
        val adapter = GroupAdapter<GroupieViewHolder>()
        val mytaskslistner = mytasksref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adapter.clear()
                for (tasksnapshot in snapshot.children){
                    adapter.add(Task(tasksnapshot.value.toString()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Get Tasks", "loadtasks:onCancelled", error.toException())
            }
        })
        recycler.adapter = adapter


        return view
    }


}

class Task(val task: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.tasktext.text = task
    }

    override fun getLayout(): Int {
        return R.layout.rsr_single_task
    }


}
