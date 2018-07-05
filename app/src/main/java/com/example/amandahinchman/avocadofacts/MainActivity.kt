package com.example.amandahinchman.avocadofacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Declare our View Variables
    private lateinit var factTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the Views from the layout
        factTextView = findViewById(R.id.avocadoFact)

    }
}
