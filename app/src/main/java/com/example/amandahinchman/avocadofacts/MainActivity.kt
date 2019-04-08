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
        moarButton.setOnClickListener {
            NewAvocadoFactTask(this).execute()
        }

    }

    fun updateAvocadoFact(fact: String) {
        factTextView.text = fact
    }

    /**
     * AsyncTask<Params, Progress, Result> signature interface:
     * - params: the type of the parameters sent to the task upon
     *             execution
     * - progress: the type of the progress units published during the
     *             background computation
     * - result: the type of the result of the background computation
     */
    private class NewAvocadoFactTask(activity: MainActivity) : AsyncTask<Void, Void, String>() {
        var reference: WeakReference<MainActivity> = WeakReference(activity)

        companion object {
            const val TAG: String = "NewAvocadoFactTask"
        }
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

        override fun doInBackground(vararg params: Void?): String {
            Log.v(TAG, "doInBackground is running on ${Thread.currentThread().name}")

            val randomGen = Random()
            val randomNumber = randomGen.nextInt(10)
            return facts[randomNumber]
        }

        override fun onPostExecute(result: String?) {
            Log.v(TAG, "onPostExecute is running on ${Thread.currentThread().name}")
            super.onPostExecute(result)
            result?.let {
                reference.get()?.updateAvocadoFact(it)  // if reference.get is not null execute the function
            }
        }
    }
}
