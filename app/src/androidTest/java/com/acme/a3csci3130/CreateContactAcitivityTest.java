package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by james on 3/14/2018.
 */
public class CreateContactAcitivityTest {

    @Rule
    public ActivityTestRule<MainActivity> main =
            new ActivityTestRule<> (MainActivity.class);

    @Test
    public void submitInfoButton() throws Exception {
        main.getActivity();
        SystemClock.sleep(1000);

        // Create a new contact to be sure there is at least one contact
        onView(withId(R.id.submitButton)).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.businessNumber)).perform(typeText("666555444"));
        onView(withId(R.id.primaryBusiness)).perform(typeText("fisher"));
        onView(withId(R.id.name)).perform(typeText("test"));
        onView(withId(R.id.submitButton)).perform(closeSoftKeyboard()).perform(click());
        SystemClock.sleep(1000);
        onView(withText("test")).check(matches(isDisplayed()));
        onView(withText("test")).perform(click());
        onView(withText("666555444")).check(matches(isDisplayed()));
        onView(withText("fisher")).check(matches(isDisplayed()));
        onView(withText("test")).check(matches(isDisplayed()));
    }

}