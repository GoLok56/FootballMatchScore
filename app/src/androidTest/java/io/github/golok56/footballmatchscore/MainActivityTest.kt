package io.github.golok56.footballmatchscore

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.github.golok56.footballmatchscore.R.id.*
import io.github.golok56.footballmatchscore.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testScheduleFavorite() {
        onView(withId(rvMainLeagues)).check(matches(isDisplayed()))
        onView(withText("Favorites")).check(matches(isDisplayed()))
        onView(withText("Favorites")).perform(click())
        onView(withText("Belum ditemukan pertandingan favorit")).check(matches(isDisplayed()))
        pressBack()

        onView(withText("Australian A-League")).perform(click())

        Thread.sleep(2000)

        onView(withText("Perth Glory")).check(matches(isDisplayed()))
        onView(withText("Perth Glory")).perform(click())
        onView(withId(ivScheduleDetailHome)).check(matches(isDisplayed()))
        onView(withId(ivScheduleDetailAway)).check(matches(isDisplayed()))
        onView(withId(fabScheduleDetail)).check(matches(isDisplayed()))
        onView(withId(fabScheduleDetail)).perform(click())

        pressBack()
        pressBack()

        onView(withText("Favorites")).perform(click())
        onView(withText("Perth Glory")).check(matches(isDisplayed()))
        onView(withText("Perth Glory")).perform(click())
        onView(withId(fabScheduleDetail)).perform(click())
    }

    @Test
    fun testTeamFavorite() {
        onView(withId(rvMainLeagues)).check(matches(isDisplayed()))
        onView(withText("Favorites")).check(matches(isDisplayed()))
        onView(withText("Favorites")).perform(click())
        onView(withId(menu_bnv_team)).perform(click())
        onView(withText("Belum ditemukan tim favorit")).check(matches(isDisplayed()))
        pressBack()

        onView(withText("Australian A-League")).perform(click())
        onView(withId(menu_bnv_team)).perform(click())

        onView(withText("Melbourne Victory")).check(matches(isDisplayed()))
        onView(withText("Melbourne Victory")).perform(click())
        onView(withId(ivTeamDetailAddFavs)).check(matches(isDisplayed()))
        onView(withId(ivTeamDetailAddFavs)).perform(click())

        pressBack()
        pressBack()

        onView(withText("Favorites")).perform(click())
        onView(withId(menu_bnv_team)).perform(click())
        onView(withText("Melbourne Victory")).check(matches(isDisplayed()))
        onView(withText("Melbourne Victory")).perform(click())
        onView(withId(ivTeamDetailAddFavs)).check(matches(isDisplayed()))
        onView(withId(ivTeamDetailAddFavs)).perform(click())
    }
}