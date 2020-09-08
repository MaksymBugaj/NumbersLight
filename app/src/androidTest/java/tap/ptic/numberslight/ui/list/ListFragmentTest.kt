package tap.ptic.numberslight.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import kotlinx.android.synthetic.main.list_fragment.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import tap.ptic.numberslight.R
import tap.ptic.numberslight.ui.NumbersActivity
import tap.ptic.numberslight.ui.adapter.NumbersRecyclerAdapter

class ListFragmentTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(NumbersActivity::class.java)
    /*
    * Recycler is visible
    */
    @Test
    fun recyclerVisible(){
        //yeah, I know its bad :/
        Thread.sleep(2000)
        onView(withId(R.id.listFragment_recyclerView)).check(matches(isDisplayed()))
    }

    /*
    * It is possible to click on item
    */
    @Test
    fun recyclerItemClick(){
        //yeah, I know its bad :/
        Thread.sleep(2000)
        onView(withId(R.id.listFragment_recyclerView))
            .perform(actionOnItemAtPosition<NumbersRecyclerAdapter.ViewHolder>(1, click()))


    }
}