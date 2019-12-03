package com.karine.mynews.controllers.activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.karine.mynews.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SwipeEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void swipeEspressoTest() {
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Top Stories"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Most Popular"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView2.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.activity_main_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Business"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_tabs),
                                        0),
                                2),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.activity_main_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("Most Popular"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.activity_main_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        viewPager3.perform(swipeRight());

        ViewInteraction tabView5 = onView(
                allOf(withContentDescription("Top Stories"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView5.perform(click());

        ViewInteraction viewPager4 = onView(
                allOf(withId(R.id.activity_main_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        viewPager4.perform(swipeRight());

        //verify if Most Popular is display with swipe
        onView(withId(R.id.activity_main_viewpager))
                .perform(swipeLeft());
        onView(withText("MOST POPULAR"))
               .check(matches(isDisplayed()));
        //verify if Top Stories is display with swipe
        onView(withId(R.id.activity_main_viewpager))
                .perform(swipeRight());
        onView(withText("TOP STORIES"))
                .check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
