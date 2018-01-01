package com.ag_automation_demo;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ag_automation_demo.EspressoUtility.withErrorInInputLayout;
import static org.hamcrest.Matchers.allOf;

/**
 * Utility class to make for easy to read espresso assertions
 */

public class Assert
{
    public static void viewDisplayedWithId(final @IdRes int resourceId)
    {
        onView(withId(resourceId)).check(matches(isDisplayed()));
    }

    public static void viewDoesNotExistWithId(final @IdRes int resourceId)
    {
        onView(withId(resourceId)).check(doesNotExist());
    }

    public static void viewDisplayedWithText(final String text)
    {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public static void viewDisplayedWithText(final @StringRes int resourceId)
    {
        onView(withText(resourceId)).check(matches(isDisplayed()));
    }

    public static void viewDisplayedWithIdAndText(final @IdRes int resourceId, final String text)
    {
        onView(allOf(withId(resourceId), withText(text))).check(matches(isDisplayed()));
    }

    public static void viewDisplayedWithIdAndText(final @IdRes int resourceId, final @StringRes int stringResourceId)
    {
        onView(allOf(withId(resourceId), withText(stringResourceId))).check(matches(isDisplayed()));
    }

    public static void errorDisplayedWithTextOnInputWithId(final @IdRes int resourceId, final String errorMessage)
    {
        onView(withId(resourceId)).check(matches(withErrorInInputLayout(errorMessage)));
    }

    public static void errorDisplayedWithTextOnInputWithId(final @IdRes int resourceId, final @StringRes int stringResourceId)
    {
        onView(withId(resourceId)).check(matches(withErrorInInputLayout(stringResourceId)));
    }

}
