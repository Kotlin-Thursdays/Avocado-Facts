package com.example.amandahinchman.avocadofacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        more.setOnClickListener { getRandomFact() }
    }

    fun updateAvocadoFact(fact: String) {
        avocadoFact.text = fact
    }

    fun getRandomFact(){
        doAsync{
            val getFacts = FactProvider.getInstance().getFacts()
            val randomNum = Random().nextInt(getFacts.size)
            val fact = getFacts[randomNum]

            Log.v(MainActivity.TAG, "doAsync is running on ${Thread.currentThread().name}")

            uiThread {
              updateAvocadoFact(fact)
              Log.v(MainActivity.TAG, "uiThread is running on ${Thread.currentThread().name}")
            }
        }
    }

    companion object {
        const val TAG: String = "MainActivity"

        fun getInstance() = MainActivity()
    }
}
