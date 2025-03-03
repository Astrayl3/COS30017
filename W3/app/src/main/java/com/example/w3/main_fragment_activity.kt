package com.example.w3

import android.os.Bundle
import android.os.PersistableBundle
import  androidx.appcompat.app.AppCompatActivity

class main_fragment_activity : AppCompatActivity(){
    override fun onCreate(saveInstancesState: Bundle?){
        super.onCreate(saveInstancesState)
        setContentView(R.layout.mainfragment_layout)
    }
}