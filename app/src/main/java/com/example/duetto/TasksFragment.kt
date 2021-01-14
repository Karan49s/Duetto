package com.example.duetto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.*
import kotlinx.android.synthetic.main.rsr_single_task.view.*


class TasksFragment : Fragment() {

    lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        recycler = view.findViewById(R.id.taskrecycler)
        val adapter = GroupAdapter<GroupieViewHolder>()
        val task = "well it's a task"
        adapter.add(Task(task))
        adapter.add(Task("its another task"))
        adapter.add(Task("complete it too"))
        adapter.add(Task("ok bye"))
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
