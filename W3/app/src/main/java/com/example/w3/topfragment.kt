package com.example.w3

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class topfragment: Fragment {
    constructor()
    lateinit var FirstName : EditText
    lateinit var LastName : EditText
    lateinit var Ok : Button
    lateinit var mainActivity: main_fragment_activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.topfragment_layout, container, false)
        FirstName = view.findViewById(R.id.FirstName)
        LastName = view.findViewById(R.id.LastName)
        Ok = view.findViewById(R.id.Ok)
        return view
    }
}