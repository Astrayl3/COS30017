package com.example.rentwithintent

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(DetailActivity::class.java)

    @Test
    fun displayItemDetailsOnLaunch() {
        val intent = Intent().putExtra("item", RentalItem("Bike", 10, 4.5f, "New"))
        ActivityScenario.launch<DetailActivity>(intent)

        onView(withId(R.id.detail_name)).check(matches(withText("Bike")))
    }

}
