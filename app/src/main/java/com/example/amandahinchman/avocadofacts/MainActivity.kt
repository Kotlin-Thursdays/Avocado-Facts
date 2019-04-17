package com.example.amandahinchman.avocadofacts

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.lang.ref.WeakReference
import java.util.*

class MainActivity : AppCompatActivity() {

    // Declare our View Variables
    private lateinit var factTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the Views from the layout
        factTextView = findViewById(R.id.avocadoFact)

        val moarButton = findViewById<Button>(R.id.more)
        moarButton.setOnClickListener { NewAvocadoFactTask(this).execute() }
    }

    fun updateAvocadoFact(fact: String) { factTextView.text = fact }

    private class NewAvocadoFactTask(activity: MainActivity) : AsyncTask<Void, Void, String>() {
        var reference: WeakReference<MainActivity> = WeakReference(activity)

        companion object {
            const val TAG: String = "NewAvocadoFactTask"
        }

        override fun doInBackground(vararg params: Void?): String {
            Log.v(TAG, "doInBackground is running on ${Thread.currentThread().name}")

            val randomGen = Random()
            val randomNumber = randomGen.nextInt(getFacts().size)
            return getFacts()[randomNumber]
        }

        override fun onPostExecute(result: String?) {
            Log.v(TAG, "onPostExecute is running on ${Thread.currentThread().name}")
            super.onPostExecute(result)
            result?.let {
                reference.get()?.updateAvocadoFact(it)
                // if reference.get is not null execute the function
            }
        }

    }

}
