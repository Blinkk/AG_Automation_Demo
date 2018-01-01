package com.ag_automation_demo;

import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * Custom espresso matchers to test views that are not supported out of the box
 */

public class EspressoUtility
{
    public static Matcher<View> withErrorInInputLayout(final String string)
    {
        return withErrorInInputLayout(is(string));
    }

    public static Matcher<View> withErrorInInputLayout(final @StringRes int resourceId)
    {
        String string = InstrumentationRegistry.getTargetContext().getResources().getString(resourceId);

        return withErrorInInputLayout(is(string));
    }

    private static Matcher<View> withErrorInInputLayout(final Matcher<String> stringMatcher)
    {
        checkNotNull(stringMatcher);

        return new BoundedMatcher<View, TextInputEditText>(TextInputEditText.class)
        {
            String actualError = "";

            @Override
            public void describeTo(Description description)
            {
                description.appendText("with error: ");
                stringMatcher.describeTo(description);
                description.appendText("But got: "); // Can add more information here
            }

            @Override
            public boolean matchesSafely(TextInputEditText textInputEditText)
            {
                CharSequence error = textInputEditText.getError();
                if (error != null)
                {
                    actualError = error.toString();
                    return stringMatcher.matches(actualError);
                }
                return false;
            }
        };
    }
}
