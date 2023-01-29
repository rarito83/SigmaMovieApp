package com.example.sigmamovieapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.sigmamovieapp.util.DataDummy
import com.example.sigmamovieapp.util.EspressoIdlingResource
import com.example.sigmamovieapp.view.MovieActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieActivityTest {

    private val movDummy = DataDummy.getMovies()

    @Before
    fun setUp() {
        ActivityScenario.launch(MovieActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movDummy.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                7
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                7,
                ViewActions.click()
            )
        )

        onView(withId(R.id.tvTitle_mov))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.imgPoster_mov))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvRating_mov))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvRelease_mov))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvGenres_mov))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvDescription_mov))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}