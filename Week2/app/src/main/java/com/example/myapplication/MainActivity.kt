package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var button1: Button;
    lateinit var button2: Button;
    lateinit var txt: EditText;
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txt = findViewById(R.id.txtName);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
    }


    override fun onClick(v: View?) {

        var setText = txt.text;
        when (v?.id) {
            R.id.button1 -> {
                /*txt.setText("Yolo");*/
                var Cars = arrayOf("Volvo", "Huyndai", "Toyota");
                Cars.set(1, "Kia")
                txt.setText(Cars.joinToString())
                Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show()
            }
 
            R.id.button2 -> {
                txt.setText("Cancel");
            }
        }
    }

}
