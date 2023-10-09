package com.example.dz23test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @Rule
    fun activityRule(): ActivityScenarioRule<MainActivity> {
        return ActivityScenarioRule(MainActivity::class.java)
    }

    @Test
    fun when_launch_textView_shon_isBlank() {
        Espresso.onView(ViewMatchers.withId(R.id.tv_main))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun when_press_button_textView_isNotBlank() {
        Espresso.onView(ViewMatchers.withId(R.id.bt_main))
            .perform(ViewActions.click())
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(R.id.tv_main))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))
    }
}