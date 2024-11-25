package com.example.otchallenge

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewDisplaysBooks() {
        // Trigger the action that calls fetchBooks() (e.g., swipe to refresh)
        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())

        // Verify that RecyclerView has the expected number of items
        onView(withId(R.id.recyclerView))
            .check(matches(hasItemCount(1)))  // Assuming 1 book is returned

        // Check that the book title is displayed in the RecyclerView
        onView(withText("TO DIE FOR")).check(matches(isDisplayed()))
    }

    private fun hasItemCount(expectedCount: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("RecyclerView should have $expectedCount items.")
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is RecyclerView) {
                    return false
                }
                val adapter = view.adapter
                return adapter?.itemCount!! >= expectedCount
            }
        }
    }
}