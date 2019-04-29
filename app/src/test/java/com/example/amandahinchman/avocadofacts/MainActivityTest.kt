package com.example.amandahinchman.avocadofacts

import android.widget.TextView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.*

/**
 * MainActivity unit test, which will execute on the development machine (host).
 *
 * Tests the business logic
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainActivityTest {
    /**
     * Initializes mocks annotated with Mock, so that explicit usage of
     * `MockitoAnnotations.initMocks(Object)`
     * is not necessary. Mocks are initialized before each test method.
     * Also validates framework usage after each test method.
     */
    @get: Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var factProvider: FactProvider

    @Mock
    lateinit var view: MainActivity

    lateinit var textView: TextView

    private val randomFacts = arrayOf(
            "Random Avocado Fact #1",
            "Random Avocado Fact #2",
            "Random Avocado Fact #3",
            "Random Avocado Fact #4",
            "Random Avocado Fact #5"
    )

    @Before
    fun setup() {
        view = MainActivity.getInstance()

        // TODO figure out how to properly inject avocadoFact
        /*textView = view.stub {
            on { avocadoFact }.thenReturn(mock())
        }.avocadoFact*/
    }

    @Test
    fun `Change avocado fact`() {
        // for any calls made that returns NPE i.e. avocadoFact you'll have
        // mock the call

        val getFacts = factProvider.stub {
            on { getFacts() }.thenReturn(randomFacts)
        }
        val avocadoFacts = getFacts.getFacts()
        val randomNum = Random().nextInt(avocadoFacts.size)

        val randomFact = avocadoFacts[randomNum]

        view.getRandomFact()
        Thread.sleep(1000)
        assertEquals(randomFact, view.newFact)
        // verify(view).updateAvocadoFact(randomFact)
    }
}
