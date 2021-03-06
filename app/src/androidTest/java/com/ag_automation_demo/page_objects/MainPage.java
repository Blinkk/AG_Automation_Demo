package com.ag_automation_demo.page_objects;

import com.ag_automation_demo.Assert;
import com.ag_automation_demo.R;
import com.ag_automation_demo.utility.Utility;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Main page object
 */

public class MainPage implements IPageObject
{
    public MainPage()
    {

    }

    @Override
    public MainPage isLoadSuccessful()
    {
        Assert.viewDisplayedWithId(R.id.fab);

        return this;
    }

    public MainPage clickNewEntryFab()
    {
        onView(withId(R.id.fab))
                .perform(click());

        return this;
    }

    public MainPage addNewEntryName(String name)
    {
        onView(withId(R.id.et_input))
                .perform(typeText(name));

        return this;
    }

    public MainPage cancelNewEntryDialog()
    {
        onView(withText(R.string.cancel))
                .perform(click());

        return this;
    }

    public CalculatorPage confirmNewEntryDialog()
    {
        onView(withText(R.string.ok))
                .perform(click());

        return new CalculatorPage();
    }

    // Flow into next page
    public CalculatorPage addNewEntry()
    {
        onView(withId(R.id.fab))
                .perform(click());

        int randomSuffix = Utility.generateRandomInteger(0, 10000);
        onView(withId(R.id.et_input))
                .perform(typeText("Test car " + randomSuffix));

        onView(withText(R.string.ok))
                .perform(click());

        return new CalculatorPage();
    }
}
