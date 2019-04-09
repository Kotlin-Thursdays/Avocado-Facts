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
        private val facts = arrayOf("The world record for the largest avocado is 5 pounds, 3.6 ounces. This is especially impressive because the average avocado only weighs 6 ounces.",
                "This fruit – yes it is a fruit! – was not originally named \"avocado\". The word \"avocado\" comes from " +
                        "the Spanish word aguacate, which is from the the Nahuatl word ahuacatl. When I lived in Chile, we called it palta, which is its Quechua name. It's also sometimes called the alligator pear due to the shape and rough green skin (cut it open and it looks like an alligator eye, too). I prefer to call it palta; my strong love for avocados started in Chile.",
                "The skin of an avocado can be toxic – to cats and dogs. While your pets (besides birds) can eat the inside of an avocado, the skin can be toxic to cats and dogs.",
                "All avocados are picked by hand.Avocados are harvested by using a 16-foot pole to reach the high-hanging fruit. Avocados tend to be more expensive than other fruits because of the high cost of labor.",
                "Avocados are high in potassium, fiber, and antioxidants.Avocados are actually higher in potassium than bananas.",
                "Avocados contain four grams of protein, making them the fruit with the highest protein content!",
                "Avocado trees do not self-pollinate; they need another avocado tree close by in order to grow. Avocados are an Aztec symbol of love and fertility, and they also grow in pairs on trees.",
                "On average, 53.5 million pounds of guacamole are eaten every Super Bowl Sunday, enough to cover a football field more than 20 feet thick.8",
                "A single California Avocado tree can produce on average about 60 pounds or 150 fruit a year.",
                "Scientists have unearthed evidences from a cave in Mexico’s Coxcatlan in Mexico, which reveal that avocados were used as far back as in 10,000 BCE.")

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
