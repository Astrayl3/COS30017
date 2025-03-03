package com.example.w3

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class bottomfragment: Fragment {
    constructor()
    lateinit var Button : Button
    lateinit var mainActivity: main_fragment_activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.bottomfragment_layout, container, false)
        Button = view.findViewById(R.id.Button)

        return view
    }
}