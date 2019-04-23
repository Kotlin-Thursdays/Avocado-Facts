package com.example.amandahinchman.avocadofacts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "MainActivity"
    }
    // Declare our View Variables
    private lateinit var factTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the Views from the layout
        factTextView = findViewById(R.id.avocadoFact)

        val moarButton = findViewById<Button>(R.id.more)
        moarButton.setOnClickListener { getRandomFact() }
    }

    fun updateAvocadoFact(fact: String) {
        factTextView.text = fact
    }

    private fun getRandomFact(){
        doAsync{
            val randomGen = Random()
            val randomNumber = randomGen.nextInt(10)
            val fact = getFacts()[randomNumber]
            Log.v(MainActivity.TAG, "doAsync is running on ${Thread.currentThread().name}")
          uiThread {
              updateAvocadoFact(fact)
              Log.v(MainActivity.TAG, "uiThread is running on ${Thread.currentThread().name}")
          }
        }
    }
}
