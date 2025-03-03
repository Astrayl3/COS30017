package com.example.myapplication

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


/*class MainActivity : ComponentActivity() {
    lateinit var IsStudents: RecyclerView
    var strList: Array<String> = arrayOf("Nguyen", "Bui", "Phan", "Dang")
    var imgList: Array<Int> = arrayOf(R.drawable.ic_launcher_background,)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.template_layout)
        IsStudents = findViewById(R.id.IsStudents)
        IsStudents.layoutManager = LinearLayoutManager(this)
        var data = ArrayList<DataClass>();
        for (i in strList.indices) {
            data.add(DataClass(strList[i].toString(), imgList[i]))
        }
        var adapter = RecyclerViewAdapter(data)
        IsStudents.setAdapter(adapter);
    }
    *//*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.template_layout)

        IsStudents = findViewById(R.id.IsStudents)
        IsStudents.layoutManager = LinearLayoutManager(this)
        IsStudents.setHasFixedSize(true)

        val data = ArrayList<DataClass>()
        for (i in strList.indices) {
            data.add(DataClass(strList[i]))
        }

        val adapter = RecyclerViewAdapter(data)
        IsStudents.adapter = adapter
    }
}*//*
}*/
class MainActivity : ComponentActivity() {
    lateinit var IsStudents: RecyclerView
    var strList: Array<String> = arrayOf("Nguyen", "Bui", "Phan", "Dang")

    // Ensure you have the same number of images as names
    var imgList: Array<Int> = arrayOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.template_layout)

        IsStudents = findViewById(R.id.IsStudents)
        IsStudents.layoutManager = LinearLayoutManager(this)

        // Create data list with names & images
        val data = ArrayList<DataClass>()
        for (i in strList.indices) {
            data.add(DataClass(strList[i], imgList[i])) // Now DataClass has 2 parameters
        }

        // Set adapter
        val adapter = RecyclerViewAdapter(data)
        IsStudents.adapter = adapter
    }
}

data class DataClass(val StudentNames: String, val StudentImage: Int)

class RecyclerViewAdapter(private val mList: List<DataClass>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_re_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val element = mList[position]
        holder.textView.text = element.StudentNames
        holder.imageView.setImageResource(element.StudentImage) // Display the image

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked: ${element.StudentNames}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.txtName)
        val imageView: ImageView = itemView.findViewById(R.id.imageView) // Add ImageView
    }
}


/*class RecyclerViewAdapter(private val mList: List<DataClass>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.template_re_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val element = mList[position]
        holder.textView.text = element.StudentNames


        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked: ${element.StudentNames}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.txtName)
    }
}*/
