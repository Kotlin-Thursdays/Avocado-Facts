package com.example.amandahinchman.avocadofacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    // Declare our View Variables
    private lateinit var factTextView: TextView

    private val facts = arrayOf("fact 1",
            "fact 2",
            "fact 3",
            "fact 4",
            "fact 5",
            "fact 6",
            "fact 7",
            "fact 8",
            "fact 9",
            "fact 10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the Views from the layout
        factTextView = findViewById(R.id.avocadoFact)

        var moarButton = findViewById<Button>(R.id.more)
        moarButton.setOnClickListener {
            val randomGen = Random()
            val randomNumber = randomGen.nextInt(10)
            factTextView.text = facts.get(randomNumber)
        }



    }
}
