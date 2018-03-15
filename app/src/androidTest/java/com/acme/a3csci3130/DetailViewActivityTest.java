package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Paul on 3/14/2018.
 */
public class DetailViewActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> main =
            new ActivityTestRule<> (MainActivity.class);

    @Test
    public void updateBusinessNumber() throws Exception {

        // Generate a random business Number
        Random r = new Random();
        int i1 = r.nextInt(10);
        int i2 = r.nextInt(10);
        int i3 = r.nextInt(10);
        int i4 = r.nextInt(10);
        int i5 = r.nextInt(10);
        int i6 = r.nextInt(10);
        int i7 = r.nextInt(10);
        int i8 = r.nextInt(10);
        int i9 = r.nextInt(10);

        String businessNumber = (Integer.toString(i1)) + (Integer.toString(i2)) + (Integer.toString(i3)) +
                (Integer.toString(i4)) + (Integer.toString(i5)) + (Integer.toString(i6)) + (Integer.toString(i7))
                + (Integer.toString(i8)) + (Integer.toString(i9));

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

        // check if the businessNumber's contact is updated
        onView(withText("test")).perform(click()).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText(businessNumber));
        onView(withId(R.id.updateButton)).perform(click());
        SystemClock.sleep(500);
        onView(withText("test")).perform(click()).perform(click());
        SystemClock.sleep(500);
        onView(withText(businessNumber)).check(matches(isDisplayed()));

    }

    @Test
    public void eraseContact() throws Exception {
        main.getActivity();
        SystemClock.sleep(1000);
        onView(withText("test")).perform(click()).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText("test")).check(doesNotExist());
    }

}